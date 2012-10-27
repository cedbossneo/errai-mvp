/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Event fired when a user refuses to leave a page. See
 * {@link PlaceManager#setOnLeaveConfirmation}.
 *
 * @see NavigationEvent
 *
 * @author Christian Goudreau
 */
public final class NavigationRefusedEvent extends
    GwtEvent<NavigationRefusedHandler> {
  private static final Type<NavigationRefusedHandler> TYPE = new Type<NavigationRefusedHandler>();

  /**
   * Fires a {@link org.jboss.errai.mvp.client.events.NavigationRefusedEvent}
   * into a source that has access to an {@link com.google.web.bindery.event.shared.EventBus}.
   *
   * @param source The source that fires this event ({@link com.google.gwt.event.shared.HasHandlers}).
   */
  public static void fire(final HasHandlers source) {
    source.fireEvent(new NavigationRefusedEvent());
  }

  public static Type<NavigationRefusedHandler> getType() {
    return TYPE;
  }

  @Override
  public Type<NavigationRefusedHandler> getAssociatedType() {
    return getType();
  }

  @Override
  protected void dispatch(NavigationRefusedHandler handler) {
    handler.onNavigationRefused(this);
  }
}