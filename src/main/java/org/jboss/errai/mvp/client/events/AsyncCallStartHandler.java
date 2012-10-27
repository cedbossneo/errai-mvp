/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for {@link AsyncCallStartEvent}.
 *
 * @author Philippe Beaudoin
 */
public interface AsyncCallStartHandler extends EventHandler {
  void onAsyncCallStart(final AsyncCallStartEvent asyncCallStartEvent);
}