package org.jboss.errai.mvp.client.proxy.deffered;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.proxy.Proxy;
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
 * Heure: 12:32
 */
public class DefferedEventImpl<P extends Presenter<?>> implements DefferedEvent<P> {
    private final Class<P> presenterClass;
    private final Event event;

    public DefferedEventImpl(Event event, Class<P> presenterClass) {
        this.event = event;
        this.presenterClass = presenterClass;
    }

    @Override
    public void registerEvent(EventBus eventBus, PlaceManager placeManager) {
        Proxy<P> proxy = ProxyManager.getPresenterProxy(presenterClass);
        eventBus.addHandler(event.getAssociatedType(), proxy);
    }

    @Override
    public Class<P> getPresenterClass() {
        return presenterClass;
    }
}
