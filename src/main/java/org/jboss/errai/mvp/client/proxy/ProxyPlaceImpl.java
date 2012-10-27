/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.proxy;

import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.places.Place;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.presenters.Presenter;

/**
 * A useful mixing class to define a {@link Proxy} that is also a {@link org.jboss.errai.mvp.client.places.Place}.
 * See {@link ProxyPlaceAbstract} for more details.
 *
 * @param <P> Type of the associated {@link Presenter}.
 *
 * @author David Peterson
 * @author Philippe Beaudoin
 */
public class ProxyPlaceImpl<P extends Presenter<?>> extends
    ProxyPlaceAbstract<P, Proxy<P>> {
    /**
     * Creates a {@link org.jboss.errai.mvp.client.proxy.ProxyPlaceAbstract}. That is, the {@link org.jboss.errai.mvp.client.proxy.Proxy} of a
     * {@link org.jboss.errai.mvp.client.presenters.Presenter} attached to a {@link org.jboss.errai.mvp.client.places.Place}. This presenter can be
     * invoked by setting a history token that matches its name token in the URL
     * bar.
     */
    public ProxyPlaceImpl(Proxy<P> proxy, Place place) {
        super(proxy, place);
    }
}
