/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.proxy.deffered;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.proxy.Proxy;
import org.jboss.errai.mvp.client.proxy.ProxyManager;

public class DefferedEventImpl<P extends Presenter<?>> implements DefferedEvent<P> {
    private final Class<P> presenterClass;
    private final Event.Type type;

    public DefferedEventImpl(Event.Type type, Class<P> presenterClass) {
        this.type = type;
        this.presenterClass = presenterClass;
    }

    @Override
    public void registerEvent(EventBus eventBus, PlaceManager placeManager) {
        Proxy<P> proxy = ProxyManager.getPresenterProxy(presenterClass);
        eventBus.addHandler(type, proxy);
    }

    @Override
    public Class<P> getPresenterClass() {
        return presenterClass;
    }
}
