/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.client.proxy.deffered;

import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.proxy.Proxy;
import org.jboss.errai.mvp.client.proxy.ProxyImpl;

public class DefferedProxyImpl<P extends Presenter<?>> implements DefferedProxy<P> {
    private final ProxyImpl<P> proxy;
    private final Class<P> presenterClass;

    public DefferedProxyImpl(ProxyImpl<P> proxy, Class<P> presenterClass) {
        this.proxy = proxy;
        this.presenterClass = presenterClass;
    }

    @Override
    public Proxy makeProxy(EventBus eventBus, PlaceManager placeManager) {
        proxy.setEventBus(eventBus);
        return proxy;
    }

    @Override
    public Class<P> getPresenterClass() {
        return presenterClass;
    }
}
