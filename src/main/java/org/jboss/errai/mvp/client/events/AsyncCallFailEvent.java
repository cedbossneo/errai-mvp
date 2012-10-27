/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Event fired after any asynchronous call to the server performed by GWTP MVP returns with
 * Such asynchronous calls only occur when using code splitting.
 *
 * @see AsyncCallFailHandler
 * @see AsyncCallStartEvent
 * @see AsyncCallSucceedEvent
 *
 * @author Philippe Beaudoin
 */
public class AsyncCallFailEvent extends GwtEvent<AsyncCallFailHandler> {
  private static final Type<AsyncCallFailHandler> TYPE = new Type<AsyncCallFailHandler>();

  /**
   * Fires a {@link AsyncCallFailEvent}
   * into a source that has access to an {@link com.google.web.bindery.event.shared.EventBus}.
   *
   * @param source The source that fires this event ({@link com.google.web.bindery.event.shared.EventBus}).
   * @param caught failure encountered while executing a remote procedure call.
   */
  public static void fire(EventBus source, Throwable caught) {
    source.fireEvent(new AsyncCallFailEvent(caught));
  }

  /**
   * Fires a {@link AsyncCallFailEvent}
   * into a source that has access to an {@link com.google.web.bindery.event.shared.EventBus}.
   * @deprecated Use {@link #fire(com.google.web.bindery.event.shared.EventBus, Throwable)} instead.
   *
   * @param source The source that fires this event ({@link com.google.gwt.event.shared.HasHandlers}).
   * @param caught failure encountered while executing a remote procedure call.
   */
  @Deprecated
  public static void fire(final HasHandlers source, Throwable caught) {
    source.fireEvent(new AsyncCallFailEvent(caught));
  }

  private final Throwable caught;

 /**
  * Creates an event indicating that an asynchronous call has failed, and attach a {@link Throwable}
  * to it.
  *
  * @param caught failure encountered while executing a remote procedure call.
  */
  AsyncCallFailEvent(Throwable caught) {
    this.caught = caught;
  }

  public static Type<AsyncCallFailHandler> getType() {
    return TYPE;
  }

  @Override
  public Type<AsyncCallFailHandler> getAssociatedType() {
    return getType();
  }

  @Override
  protected void dispatch(AsyncCallFailHandler handler) {
    handler.onAsyncCallFail(this);
  }

  /**
   * Access the {@link Throwable} that was obtained when this asynchronous call failed.
   *
   * @return The {@link PlaceRequest} or {@code null} if no place request is
   *         known.
   */
  public Throwable getCaught() {
    return caught;
  }
}
