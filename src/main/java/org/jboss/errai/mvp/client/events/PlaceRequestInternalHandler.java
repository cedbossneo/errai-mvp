/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * Proxy should implement this class.
 *
 * @author Philippe Beaudoin
 */
public interface PlaceRequestInternalHandler extends EventHandler {
  /**
   * Called when something has requested a new place. Should be implemented by
   * instances which can show the place.
   *
   * @param event The event.
   */
  void onPlaceRequest(PlaceRequestInternalEvent event);
}
