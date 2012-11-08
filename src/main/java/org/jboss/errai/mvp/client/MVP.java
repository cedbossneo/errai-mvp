/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class MVP {
    private EventBus eventBus;

    @Produces
    private EventBus produceEventBus(){
        if (eventBus == null)
            eventBus = GWT.create(SimpleEventBus.class);
        return eventBus;
    }
}
