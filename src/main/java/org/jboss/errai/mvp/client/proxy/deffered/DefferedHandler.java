/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.proxy.deffered;

import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.presenters.Presenter;

public interface DefferedHandler<P extends Presenter<?>> {
    void registerHandler(EventBus eventBus, PlaceManager placeManager);
    Class<P> getPresenterClass();
}
