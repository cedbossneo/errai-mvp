/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * This event is fired whenever all visible presenters should
 * be reset. This is fired automatically right after navigating to a
 * new place.
 * <p />
 * The default implementation or {@link com.gwtplatform.mvp.client.RootPresenter} causes
 * {@link com.gwtplatform.mvp.client.PresenterWidget#onReset()}
 * to be called, starting from the top level presenter and going down.
 *
 * @author Philippe Beaudoin
 */
public final class ResetPresentersEvent extends
        GwtEvent<ResetPresentersHandler> {

  private static final Type<ResetPresentersHandler> type = new Type<ResetPresentersHandler>();

  /**
   * Fires a {@link ResetPresentersEvent}
   * into a source that has access to an {@link com.google.web.bindery.event.shared.EventBus}.
   *
   * @param source The source that fires this event ({@link com.google.gwt.event.shared.HasHandlers}).
   */
  public static void fire(final HasHandlers source) {
    source.fireEvent(new ResetPresentersEvent());
  }

  public static Type<ResetPresentersHandler> getType() {
    return type;
  }

  @Override
  public Type<ResetPresentersHandler> getAssociatedType() {
    return getType();
  }

  @Override
  protected void dispatch(ResetPresentersHandler handler) {
    handler.onResetPresenters(this);
  }

}
