/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.views;

/**
 * A simple handler that provides a callback method to use whenever a
 * {@link PopupView} is closed.
 *
 * @author Philippe Beaudoin
 */
public interface PopupViewCloseHandler {
  void onClose();
}
