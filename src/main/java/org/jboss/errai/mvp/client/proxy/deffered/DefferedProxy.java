/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.proxy.deffered;

import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.proxy.Proxy;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 10/24/12
 * Time: 9:56 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DefferedProxy<P extends Presenter<?>> {
    Proxy makeProxy(EventBus eventBus, PlaceManager placeManager);

    Class<P> getPresenterClass();
}
