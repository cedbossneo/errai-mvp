package org.jboss.errai.mvp.client.places;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.ioc.client.api.IOCProvider;

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
 * Heure: 12:06
 */
@Singleton
@IOCProvider
public class PlaceManagerProvider implements Provider<PlaceManager> {
    @Inject
    EventBus eventBus;

    @Inject
    TokenFormatter tokenFormatter;

    @Override
    public PlaceManager get() {
        return new PlaceManagerImpl(eventBus, tokenFormatter) {
            @Override
            public void revealDefaultPlace() {
                revealPlace(new PlaceRequest(""));
            }
        };
    }
}
