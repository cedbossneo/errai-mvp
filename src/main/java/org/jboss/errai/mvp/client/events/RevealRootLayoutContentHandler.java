/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * This is the handler interface for {@link RevealRootLayoutContentEvent}. It is
 * used solely by {@link com.gwtplatform.mvp.client.RootPresenter}.
 *
 * @author Philippe Beaudoin
 */
public interface RevealRootLayoutContentHandler extends EventHandler {

  /**
   * Called whenever a presenter wants to sets itself as the root layout content
   * of the application, that is, within GWT's
   * {@link com.google.gwt.user.client.ui.RootLayoutPanel}.
   *
   * @param event The event containing the presenter that wants to bet set as
   *          root layout content.
   */
  void onRevealRootLayoutContent(RevealRootLayoutContentEvent event);

}
