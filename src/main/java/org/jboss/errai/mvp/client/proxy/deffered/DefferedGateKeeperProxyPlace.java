/*
 * Copyright 2012 Cedric Hauber
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.jboss.errai.mvp.client.proxy.deffered;

import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.ioc.client.container.IOC;
import org.jboss.errai.mvp.client.places.Gatekeeper;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.places.PlaceWithGatekeeper;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.proxy.Proxy;
import org.jboss.errai.mvp.client.proxy.ProxyManager;
import org.jboss.errai.mvp.client.proxy.ProxyPlaceImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 10/24/12
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefferedGateKeeperProxyPlace<P extends Presenter<?>> implements DefferedProxy<P>{
    private final String token;
    private final Class<P> presenterClass;
    private final Class<? extends Gatekeeper> gateKeeper;

    public DefferedGateKeeperProxyPlace(String token, Class<P> presenterClass, Class<? extends Gatekeeper> gateKeeper) {
        this.token = token;
        this.presenterClass = presenterClass;
        this.gateKeeper = gateKeeper;
    }

    public String getToken() {
        return token;
    }

    public Class<P> getPresenterClass() {
        return presenterClass;
    }

    @Override
    public Proxy makeProxy(EventBus eventBus, PlaceManager placeManager) {
        ProxyPlaceImpl proxyPlace = new ProxyPlaceImpl<P>(ProxyManager.getPresenterProxy(presenterClass), new PlaceWithGatekeeper(token, IOC.getBeanManager().lookupBean(gateKeeper).getInstance()));
        proxyPlace.bind(placeManager, eventBus);
        return proxyPlace;
    }
}
