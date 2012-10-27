/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Philippe Beaudoin
 */
public interface ResetPresentersHandler extends EventHandler {

  void onResetPresenters(final ResetPresentersEvent resetPresentersEvent);

}
