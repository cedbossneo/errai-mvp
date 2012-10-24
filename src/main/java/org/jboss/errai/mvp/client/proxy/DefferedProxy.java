package org.jboss.errai.mvp.client.proxy;

import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.presenters.Presenter;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 10/24/12
 * Time: 9:56 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DefferedProxy {
    Proxy makeProxy(EventBus eventBus, PlaceManager placeManager);

    Class<? extends Presenter<?>> getPresenterClass();
}
