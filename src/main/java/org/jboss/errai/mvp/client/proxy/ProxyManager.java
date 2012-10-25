package org.jboss.errai.mvp.client.proxy;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.mvp.client.events.NotifyingAsyncCallback;
import org.jboss.errai.mvp.client.places.Gatekeeper;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.proxy.deffered.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 10/21/12
 * Time: 11:55 PM
 * To change this template use File | Settings | File Templates.
 */
@ApplicationScoped
public class ProxyManager {
    @Inject
    private IOCBeanManager manager;

    @Inject
    EventBus eventBus;

    @Inject
    PlaceManager placeManager;

    private static ProxyManager instance;

    static List<DefferedProxy> defferedProxies = new LinkedList<DefferedProxy>();
    static List<DefferedHandler> defferedHandlers = new LinkedList<DefferedHandler>();
    static List<DefferedEvent> defferedEvents = new LinkedList<DefferedEvent>();
    static Map<Class<? extends Presenter<?>>, Proxy> proxies = new HashMap<Class<? extends Presenter<?>>, Proxy>();
    static Map<Class<? extends Presenter<?>>, ProxyPlace> proxiesPlaces = new HashMap<Class<? extends Presenter<?>>, ProxyPlace>();

    public static <P extends Presenter<?>> void registerProxy(Class<P> presenterClass){
        DefferedProxyImpl<P> defferedProxy = new DefferedProxyImpl<P>(presenterClass);
        defferedProxies.add(defferedProxy);
    }

    public static <P extends Presenter<?>> void registerHandler(GwtEvent.Type type, Class<P> presenterClass){
        DefferedContentHandler<P> handler = new DefferedContentHandler<P>(type, presenterClass);
        defferedHandlers.add(handler);
    }

    public static <P extends Presenter<?>> void registerEvent(Event event, Class<P> presenterClass){
        DefferedEventImpl<P> defferedEvent = new DefferedEventImpl<P>(event, presenterClass);
        defferedEvents.add(defferedEvent);
    }

    public static <P extends Presenter<?>> void registerPlace(String token, Class<P> presenterClass){
        DefferedProxyPlace<P> proxyPlace = new DefferedProxyPlace<P>(token, presenterClass);
        defferedProxies.add(proxyPlace);
    }

    public static <P extends Presenter<?>> void registerPlace(String token, Class<P> presenterClass, Class<? extends Gatekeeper> gateKeeper){
        DefferedGateKeeperProxyPlace<P> proxyPlace = new DefferedGateKeeperProxyPlace<P>(token, presenterClass, gateKeeper);
        defferedProxies.add(proxyPlace);
    }

    @PostConstruct
    public void init(){
        instance = this;
        for (DefferedProxy defferedProxy : defferedProxies){
            Proxy value = defferedProxy.makeProxy(eventBus, placeManager);
            if (value instanceof ProxyPlace)
                proxiesPlaces.put(defferedProxy.getPresenterClass(), (ProxyPlace) value);
            else
                proxies.put(defferedProxy.getPresenterClass(), value);
        }
        for (DefferedHandler defferedHandler : defferedHandlers){
            defferedHandler.registerHandler(eventBus, placeManager);
        }
        for (DefferedEvent defferedEvent : defferedEvents){
            defferedEvent.registerEvent(eventBus, placeManager);
        }
    }

    public static <P extends Presenter<?>> Proxy<P> getPresenterProxy(Class<P> presenterClass){
        return proxies.get(presenterClass);
    }

    public static <P extends Presenter<?>> ProxyPlace<P> getPresenterProxyPlace(Class<P> presenterClass){
        return proxiesPlaces.get(presenterClass);
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
}
