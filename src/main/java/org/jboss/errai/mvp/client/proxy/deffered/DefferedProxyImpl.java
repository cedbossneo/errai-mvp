package org.jboss.errai.mvp.client.proxy.deffered;

import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.proxy.Proxy;
import org.jboss.errai.mvp.client.proxy.ProxyImpl;

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
 * Heure: 12:19
 */
public class DefferedProxyImpl<P extends Presenter> implements DefferedProxy {
    private final Class<P> presenterClass;

    public DefferedProxyImpl(Class<P> presenterClass) {
        this.presenterClass = presenterClass;
    }

    @Override
    public Proxy makeProxy(EventBus eventBus, PlaceManager placeManager) {
        return new ProxyImpl<P>(eventBus, presenterClass);
    }

    @Override
    public Class<P> getPresenterClass() {
        return presenterClass;
    }
}
