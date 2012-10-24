package org.jboss.errai.mvp.client.proxy;

import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.ioc.client.container.IOC;
import org.jboss.errai.mvp.client.places.Gatekeeper;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.places.PlaceWithGatekeeper;
import org.jboss.errai.mvp.client.presenters.Presenter;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 10/24/12
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefferedGateKeeperProxyPlace<P extends Presenter> implements DefferedProxy{
    private final String token;
    private final Class<P> presenterClass;
    private final Class<? extends Gatekeeper> gateKeeper;

    public DefferedGateKeeperProxyPlace(String token, Class<P> presenterClass, Class<? extends Gatekeeper> gateKeeper) {
        this.token = token;
        this.presenterClass = presenterClass;
        this.gateKeeper = gateKeeper;
    }

    public String getToken() {
        return token;
    }

    public Class<? extends Presenter<?>> getPresenterClass() {
        return (Class<? extends Presenter<?>>) presenterClass;
    }

    public Class<? extends Gatekeeper> getGateKeeper() {
        return gateKeeper;
    }

    @Override
    public Proxy makeProxy(EventBus eventBus, PlaceManager placeManager) {
        ProxyImpl proxy = new ProxyImpl(eventBus, presenterClass);
        ProxyPlaceImpl proxyPlace = new ProxyPlaceImpl(proxy, new PlaceWithGatekeeper(token, IOC.getBeanManager().lookupBean(gateKeeper).getInstance()));
        proxyPlace.bind(placeManager, eventBus);
        return proxyPlace;
    }
}
