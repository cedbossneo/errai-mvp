/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.client;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.events.LazyEventBus;
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
            eventBus = GWT.create(LazyEventBus.class);
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
