/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

/**
 * The interface for a handler that will be invoked when a place title is
 * available. For more information see
 * {@link PlaceManager#getTitle(int, org.jboss.errai.mvp.client.events.SetPlaceTitleHandler)}.
 *
 * @author Philippe Beaudoin
 */
public interface SetPlaceTitleHandler {
  /**
   * Invoked when the title of the place is available.
   *
   * @param title The place title.
   */
  void onSetPlaceTitle(String title);
}
