/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.views;

import com.google.gwt.user.client.ui.Widget;

/**
 * The interface for view classes that handles all the UI-related code for a
 * {@link Presenter}.
 *
 * @author Philippe Beaudoin
 * @author Christian Goudreau
 */
public interface View {

  /**
   * Requests the view to add content within a specific slot.
   * <p />
   * Override the default implementation and manage all the slots of your view
   * into which content can be added. You should also consider overriding
   * {@link #removeFromSlot(Object, com.google.gwt.user.client.ui.Widget)}.
   * If the view doesn't know about this slot, it can silently ignore the request.
   * <p />
   * Used by {@link PresenterWidget#addToSlot(Object, PresenterWidget)}.
   *
   * @param slot An opaque object indicating the slot to add into.
   * @param content The content to add, a {@link com.google.gwt.user.client.ui.Widget}.
   */
  void addToSlot(Object slot, Widget content);

  /**
   * Retrieves this view as a {@link com.google.gwt.user.client.ui.Widget} so that it can be inserted within
   * the DOM.
   *
   * @return This view as a DOM object.
   */
  Widget asWidget();

  /**
   * Requests the view to remove content from a specific slot.
   * <p />
   * Override the default implementation and manage all the slots of your view
   * into which content can be added and removed. You should also override
   * {@link #addToSlot(Object, com.google.gwt.user.client.ui.Widget)}.
   * If the view doesn't know about this slot, it can silently ignore the request.
   * <p />
   * Used by {@link PresenterWidget#removeFromSlot(Object, PresenterWidget)}.
   *
   * @param slot An opaque object indicating the slot to remove from.
   * @param content The content to remove, a {@link com.google.gwt.user.client.ui.Widget}.
   */
  void removeFromSlot(Object slot, Widget content);

  /**
   * Requests the view to set content within a specific slot, clearing anything
   * that was already contained there.
   * <p />
   * Override the default implementation and manage all the slots of your view
   * into which content can be placed. If the view doesn't know about this slot,
   * it can silently ignore the request. When {@code null} is passed, your
   * implementation should clear the slot.
   * <p />
   * Used by {@link PresenterWidget#setInSlot(Object, PresenterWidget)} and
   * {@link PresenterWidget#clearSlot(Object)}.
   *
   * @param slot An opaque object indicating the slot to add into.
   * @param content The content to add, a {@link com.google.gwt.user.client.ui.Widget}. Pass {@code null} to
   *          clear the slot entirely.
   */
  void setInSlot(Object slot, Widget content);
}
