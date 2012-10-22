package org.jboss.errai.mvp.client.providers;

import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.ioc.client.api.IOCProvider;
import org.jboss.errai.mvp.client.events.LazyEventBus;

import javax.inject.Provider;

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
 * Date: 22/10/12
 * Heure: 18:26
 */
@IOCProvider
@Singleton
public class EventBusProvider implements Provider<EventBus> {
    @Override
    public EventBus get() {
        return new LazyEventBus();
    }
}
