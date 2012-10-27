/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Philippe Beaudoin
 */
public interface RequestTabsHandler extends EventHandler {

  /**
   * Called whenever the {@link org.jboss.errai.mvp.client.presenters.TabContainerPresenter} is instantiated and needs
   * to know which tabs it contains.
   *
   * @param event The event.
   */
  void onRequestTabs(RequestTabsEvent event);

}
