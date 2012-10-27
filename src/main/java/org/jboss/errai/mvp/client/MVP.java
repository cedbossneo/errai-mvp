/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.places.PlaceManagerImpl;
import org.jboss.errai.mvp.client.places.PlaceRequest;
import org.jboss.errai.mvp.client.places.TokenFormatter;
import org.jboss.errai.mvp.client.proxy.ProxyManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class MVP {
    @Inject
    private ProxyManager manager;

    @Inject
    TokenFormatter tokenFormatter;

    private EventBus eventBus;
    private PlaceManager placeManager;

    @Produces
    private EventBus produceEventBus(){
        if (eventBus == null)
            eventBus = GWT.create(SimpleEventBus.class);
        return eventBus;
    }

    @Produces
    private PlaceManager producePlaceManager(){
        if (placeManager == null)
            placeManager = new PlaceManagerImpl(produceEventBus(), tokenFormatter) {
                @Override
                public void revealDefaultPlace() {
                    revealPlace(new PlaceRequest(""));
                }
            };
        return placeManager;
    }
}
