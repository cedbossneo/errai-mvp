package org.jboss.errai.mvp.client.proxy;

import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.places.PlaceImpl;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.presenters.Presenter;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 10/24/12
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefferedProxyPlace<P extends Presenter> implements DefferedProxy{
    private final String token;
    private final Class<P> presenterClass;

    public DefferedProxyPlace(String token, Class<P> presenterClass) {
        this.token = token;
        this.presenterClass = presenterClass;
    }

    public String getToken() {
        return token;
    }

    public Class<? extends Presenter<?>> getPresenterClass() {
        return (Class<? extends Presenter<?>>) presenterClass;
    }

    @Override
    public Proxy makeProxy(EventBus eventBus, PlaceManager placeManager) {
        ProxyImpl proxy = new ProxyImpl(eventBus, presenterClass);
        ProxyPlaceImpl proxyPlace = new ProxyPlaceImpl(proxy, new PlaceImpl(token));
        proxyPlace.bind(placeManager, eventBus);
        return proxyPlace;
    }
}