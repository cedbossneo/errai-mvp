/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.proxy.deffered;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.events.RevealContentHandler;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.proxy.ProxyImpl;
import org.jboss.errai.mvp.client.proxy.ProxyManager;

public class DefferedContentHandler<P extends Presenter<?>> implements DefferedHandler<P>{
    private final GwtEvent.Type type;
    private final Class<P> presenterClass;

    public DefferedContentHandler(GwtEvent.Type type, Class<P> presenterClass) {
        this.type = type;
        this.presenterClass = presenterClass;
    }

    @Override
    public void registerHandler(EventBus eventBus, PlaceManager placeManager) {
        RevealContentHandler<P> contentHandler = new RevealContentHandler<P>(eventBus, (ProxyImpl<P>) ProxyManager.getPresenterProxy(presenterClass));
        eventBus.addHandler(type, contentHandler);
    }

    @Override
    public Class<P> getPresenterClass() {
        return presenterClass;
    }

    public GwtEvent.Type getType() {
        return type;
    }
}
