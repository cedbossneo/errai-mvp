/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for {@link LockInteractionEvent}.
 *
 * @author Philippe Beaudoin
 */
public interface LockInteractionHandler extends EventHandler {
  void onLockInteraction(
          final LockInteractionEvent lockInteractionEvent);
}