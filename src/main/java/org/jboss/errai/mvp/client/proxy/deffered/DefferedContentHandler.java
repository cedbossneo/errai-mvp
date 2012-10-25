package org.jboss.errai.mvp.client.proxy.deffered;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.events.RevealContentHandler;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.proxy.ProxyImpl;
import org.jboss.errai.mvp.client.proxy.ProxyManager;

/**
 * Application Logicielle Visitors-Book
 * <p/>
 * Copyright (c) : Jade-i, 2010-2012, All rights reserved.
 * <p/>
 * IDDN.FR.001.500049.000.S.P.2011.000.20700
 * <p/>
 * Auteur : Laurent Vieille, Nicolas Mallot-Touzet, Bernard Wappler, Cedric Hauber, Joel Kinding-Kinding, Paul Duncan
 * <p/>
 * Dernière modification
 * Utilisateur: cedric
 * Date: 25/10/12
 * Heure: 12:15
 */
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
