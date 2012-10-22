package org.jboss.errai.mvp.client.proxy;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Command;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.mvp.client.events.*;
import org.jboss.errai.mvp.client.places.Place;
import org.jboss.errai.mvp.client.places.PlaceImpl;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.places.PlaceRequest;
import org.jboss.errai.mvp.client.presenters.Presenter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 10/21/12
 * Time: 11:55 PM
 * To change this template use File | Settings | File Templates.
 */
@ApplicationScoped
public class ProxyManager implements HasHandlers {
    @Inject
    private IOCBeanManager manager;

    @Inject
    EventBus eventBus;

    @Inject
    PlaceManager placeManager;

    private static ProxyManager instance;

    static Map<Place, Class<? extends Presenter<?>>> places = new HashMap<Place, Class<? extends Presenter<?>>>();

    public static void registerPlace(String token, Class<? extends Presenter<?>> presenterClass){
        places.put(new PlaceImpl(token), presenterClass);
    }

    @PostConstruct
    public void init(){
        instance = this;
        eventBus.addHandler(PlaceRequestInternalEvent.getType(),
                new PlaceRequestInternalHandler() {
                    @Override
                    public void onPlaceRequest(PlaceRequestInternalEvent event) {
                        if (event.isHandled()) {
                            return;
                        }
                        PlaceRequest request = event.getRequest();
                        Place place = getPlace(request);
                        if (place != null) {
                            event.setHandled();
                            if (canReveal(place)) {
                                handleRequest(places.get(place), request, event.shouldUpdateBrowserHistory());
                            } else {
                                event.setUnauthorized();
                            }
                        }
                    }
                });
        eventBus.addHandler(GetPlaceTitleEvent.getType(),
                new GetPlaceTitleHandler() {
                    @Override
                    public void onGetPlaceTitle(GetPlaceTitleEvent event) {
                        if (event.isHandled()) {
                            return;
                        }
                        PlaceRequest request = event.getRequest();
                        Place place = getPlace(request);
                        if (place != null) {
                            if (canReveal(place)) {
                                event.setHandled();
                                getPlaceTitle(event);
                            }
                        }
                    }
                });
        for (Place place : places.keySet()){
            RevealContentHandler rch = new RevealContentHandler(eventBus, places.get(place));
            eventBus.
        }
    }

    private Place getPlace(PlaceRequest request) {
        for (Place place : places.keySet()){
            if (matchesRequest(place, request))
                return place;
        }
        return null;
    }

    public boolean matchesRequest(Place place, PlaceRequest request) {
        return place.matchesRequest(request);
    }

    public boolean canReveal(Place place) {
        return place.canReveal();
    }

    protected void getPlaceTitle(GetPlaceTitleEvent event) {
        event.getHandler().onSetPlaceTitle(null);
    }

    private <P extends Presenter<?>> void handleRequest(Class<P> presenter, final PlaceRequest request, final boolean updateBrowserUrl) {
        getPresenter(presenter, new NotifyingAsyncCallback<P>(eventBus) {

            @Override
            public void success(final P presenter) {
                // Everything should be bound before we prepare the presenter from the
                // request,
                // in case it wants to fire some events. That's why we will do this in a
                // deferred command.
                addDeferredCommand(new Command() {
                    @Override
                    public void execute() {
                        PlaceRequest originalRequest = placeManager.getCurrentPlaceRequest();
                        presenter.prepareFromRequest(request);
                        if (originalRequest == placeManager.getCurrentPlaceRequest()) {
                            // User did not manually update place request in prepareFromRequest, update it here.
                            placeManager.updateHistory(request, updateBrowserUrl);
                        }
                        NavigationEvent.fire(placeManager, request);
                        if (!presenter.useManualReveal()) {
                            // Automatic reveal
                            manualReveal(presenter);
                        }
                    }
                });
            }
        });
    }

    public void manualReveal(Presenter<?> presenter) {
        // Reveal only if there are no pending navigation requests
        if (!placeManager.hasPendingNavigation()) {
            if (!presenter.isVisible()) {
                // This will trigger a reset in due time
                presenter.forceReveal();
            } else {
                // We have to do the reset ourselves
                ResetPresentersEvent.fire(this);
            }
        }
        placeManager.unlock();
    }

    public void manualRevealFailed() {
        placeManager.unlock();
    }

    /**
     * This method allows unit test to handle deferred command with a mechanism that doesn't
     * require a GWTTestCase.
     *
     * @param command The {@Command} to defer, see {@link com.google.gwt.user.client.DeferredCommand}.
     */
    void addDeferredCommand(Command command) {
        Scheduler.get().scheduleDeferred(command);
    }


    public static <T extends Presenter<?>> void getPresenter(Class<T> persenterClass, NotifyingAsyncCallback<T> notifyingAsyncCallback){
        notifyingAsyncCallback.prepare();
        IOCBeanDef<T> tiocBeanDef = instance.manager.lookupBean(persenterClass);
        if (tiocBeanDef == null)
            notifyingAsyncCallback.onFailure(new Throwable("Bean definition not found"));
        notifyingAsyncCallback.checkLoading();
        T bean = tiocBeanDef.getInstance();
        if (bean == null)
            notifyingAsyncCallback.onFailure(new Throwable("Error while getting bean"));
        else
            notifyingAsyncCallback.onSuccess(bean);
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEventFromSource(event, this);
    }
}
