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

package org.jboss.errai.mvp.client.proxy;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.events.NotifyingAsyncCallback;
import org.jboss.errai.mvp.client.presenters.Presenter;

/**
 * @author Philippe Beaudoin
 *
 * @param <P> The presenter's type.
 */
public class ProxyImpl<P extends Presenter<?>> implements Proxy<P> {

  protected EventBus eventBus;
  private Class<P> presenterClass;

    /**
   * Creates a Proxy class for a specific presenter.
   */
  public ProxyImpl(Class<P> presenterClass) {
      this.presenterClass = presenterClass;
  }

  @Override
  public void getPresenter(NotifyingAsyncCallback<P> callback) {
    callback.prepare();
    ProxyManager.getPresenter(presenterClass, callback);
    callback.checkLoading();
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEventFromSource(event, this);
  }

  @Override
  public final EventBus getEventBus() {
    return eventBus;
  }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public Class<P> getPresenterClass() {
        return presenterClass;
    }
}
