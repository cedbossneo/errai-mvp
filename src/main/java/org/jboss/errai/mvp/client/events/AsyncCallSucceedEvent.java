/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Event fired after any asynchronous call to the server performed by GWTP MVP has succeeded.
 * Such asynchronous calls only occur when using code splitting.
 *
 * @see AsyncCallSucceedHandler
 * @see AsyncCallStartEvent
 * @see AsyncCallFailEvent
 *
 * @author Philippe Beaudoin
 */
public class AsyncCallSucceedEvent extends GwtEvent<AsyncCallSucceedHandler> {
  private static final Type<AsyncCallSucceedHandler> TYPE = new Type<AsyncCallSucceedHandler>();

  /**
   * Fires a {@link AsyncCallSucceedEvent}
   * into a source that has access to an {@link com.google.web.bindery.event.shared.EventBus}.
   *
   * @param source The source that fires this event ({@link com.google.web.bindery.event.shared.EventBus}).
   */
  public static void fire(EventBus source) {
    source.fireEvent(new AsyncCallSucceedEvent());
  }

  /**
   * Fires a {@link AsyncCallSucceedEvent}
   * into a source that has access to an {@link com.google.web.bindery.event.shared.EventBus}.
   * @deprecated Use {@link #fire(com.google.web.bindery.event.shared.EventBus)} instead.
   *
   * @param source The source that fires this event ({@link com.google.gwt.event.shared.HasHandlers}).
   */
  @Deprecated
  public static void fire(final HasHandlers source) {
    source.fireEvent(new AsyncCallSucceedEvent());
  }

  AsyncCallSucceedEvent() {
  }

  public static Type<AsyncCallSucceedHandler> getType() {
    return TYPE;
  }

  @Override
  public Type<AsyncCallSucceedHandler> getAssociatedType() {
    return getType();
  }

  @Override
  protected void dispatch(AsyncCallSucceedHandler handler) {
    handler.onAsyncCallSucceed(this);
  }
}
