/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
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
}
