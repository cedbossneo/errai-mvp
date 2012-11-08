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

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.events.RevealContentHandler;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.proxy.ProxyImpl;
import org.jboss.errai.mvp.client.proxy.ProxyManager;

public class DefferedContentHandler<P extends Presenter<?>> implements DefferedHandler<P>{
    private final GwtEvent.Type type;
    private final Class<P> presenterClass;

    public DefferedContentHandler(GwtEvent.Type type, Class<P> presenterClass) {
        this.type = type;
        this.presenterClass = presenterClass;
    }

    @Override
    public void registerHandler(EventBus eventBus, PlaceManager placeManager) {
        RevealContentHandler<P> contentHandler = new RevealContentHandler<P>(eventBus, (ProxyImpl<P>) ProxyManager.getPresenterProxy(presenterClass));
        eventBus.addHandler(type, contentHandler);
    }

    @Override
    public Class<P> getPresenterClass() {
        return presenterClass;
    }

    public GwtEvent.Type getType() {
        return type;
    }
}
