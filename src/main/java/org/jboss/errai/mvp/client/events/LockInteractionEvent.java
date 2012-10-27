/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * This event is fired whenever interaction should be locked throughout the application,
 * usually because a navigation operation is taking place and interacting could cause
 * unexpected state switches.
 * <p />
 * This event is typically fired by the {@link PlaceManager} whenever a navigation operation
 * starts or stops. It is handled by the default {@link com.gwtplatform.mvp.client.RootPresenter} implementation.
 * Override {@link com.gwtplatform.mvp.client.RootPresenter#lockInteraction(boolean)} to customize the behaviour.
 *
 * @author Philippe Beaudoin
 */
public class LockInteractionEvent extends GwtEvent<LockInteractionHandler> {

  private static Type<LockInteractionHandler> TYPE;

  /**
   * Fires a {@link org.jboss.errai.mvp.client.events.LockInteractionEvent}
   * into a source that has access to an {@link com.google.web.bindery.event.shared.EventBus}
   * specifying whether interaction should be locked or unlocked.
   *
   * @param source The source that fires this event ({@link com.google.gwt.event.shared.HasHandlers}).
   * @param lock {@code true} to lock interaction, {@code false} to unlock it.
   */
  public static void fire(HasHandlers source, boolean lock) {
    source.fireEvent(new LockInteractionEvent(lock));
  }

  public static Type<LockInteractionHandler> getType() {
    if (TYPE == null) {
      TYPE = new Type<LockInteractionHandler>();
    }
    return TYPE;
  }

  private final boolean lock;

  public LockInteractionEvent(boolean lock) {
    this.lock = lock;
  }

  @Override
  public Type<LockInteractionHandler> getAssociatedType() {
    return getType();
  }

  public boolean shouldLock() {
    return lock;
  }

  @Override
  protected void dispatch(LockInteractionHandler handler) {
    handler.onLockInteraction(this);
  }

}
