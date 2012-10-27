/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Event fired right before any asynchronous call to the server is performed by GWTP MVP.
 * Such asynchronous calls only occur when using code splitting. You can hook on this event
 * to display a "Loading..." message if desired.
 *
 * @see AsyncCallStartHandler
 * @see AsyncCallSucceedEvent
 * @see AsyncCallFailEvent
 *
 * @author Philippe Beaudoin
 */
public class AsyncCallStartEvent extends GwtEvent<AsyncCallStartHandler> {
  private static final Type<AsyncCallStartHandler> TYPE = new Type<AsyncCallStartHandler>();

  /**
   * Fires a {@link AsyncCallStartEvent}
   * into a source that has access to an {@link com.google.web.bindery.event.shared.EventBus}.
   *
   * @param source The source that fires this event ({@link com.google.web.bindery.event.shared.EventBus}).
   */
  public static void fire(EventBus source) {
    source.fireEvent(new AsyncCallStartEvent());
  }

  /**
   * Fires a {@link AsyncCallStartEvent}
   * into a source that has access to an {@link com.google.web.bindery.event.shared.EventBus}.
   * @deprecated Use {@link #fire(com.google.web.bindery.event.shared.EventBus)} instead.
   *
   * @param source The source that fires this event ({@link com.google.gwt.event.shared.HasHandlers}).
   */
  @Deprecated
  public static void fire(final HasHandlers source) {
    source.fireEvent(new AsyncCallStartEvent());
  }

  public static Type<AsyncCallStartHandler> getType() {
    return TYPE;
  }

  AsyncCallStartEvent() {
  }

  @Override
  public Type<AsyncCallStartHandler> getAssociatedType() {
    return getType();
  }

  @Override
  protected void dispatch(AsyncCallStartHandler handler) {
    handler.onAsyncCallStart(this);
  }
}
