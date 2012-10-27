/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Philippe Beaudoin
 */
public interface ChangeTabHandler extends EventHandler {

  /**
   * Called whenever a tab contained in a {@link TabContainerPresenter} wants to change its
   * information.
   *
   * @param event The event.
   */
  void onChangeTab(ChangeTabEvent event);

}
