/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Philippe Beaudoin
 */
public interface GetPlaceTitleHandler extends EventHandler {
  /**
   * Called when something has requested the name of a new place. Should be
   * answered by instances that can show the place.
   *
   * @param event The event.
   */
  void onGetPlaceTitle(GetPlaceTitleEvent event);
}
