/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.presenters;

import org.jboss.errai.mvp.client.views.PopupView;

/**
 * @author Christian Goudreau
 */
public interface HasPopupSlot {

  /**
   * This method sets some popup content within the {@link Presenter} and
   * centers it. The view associated with the {@code content}'s presenter must
   * inherit from {@link PopupView}. The popup will be visible and the
   * corresponding presenter will receive the lifecycle events as needed.
   * <p />
   * Contrary to the {@link org.jboss.errai.mvp.client.views.View#setInSlot(Object, com.google.gwt.user.client.ui.Widget)} method, no
   * {@link org.jboss.errai.mvp.client.events.ResetPresentersEvent} is
   * fired, so {@link PresenterWidget#onReset()} is not invoked.
   *
   * @param child The popup child, a {@link PresenterWidget}. Passing {@code null}
   *          will clear the slot.
   *
   * @see #addToPopupSlot(PresenterWidget)
   */
  void addToPopupSlot(final PresenterWidget<? extends PopupView> child);

  /**
   * This method sets some popup content within the {@link Presenter}. The view
   * associated with the {@code content}'s presenter must inherit from
   * {@link PopupView}. The popup will be visible and the corresponding
   * presenter will receive the lifecycle events as needed.
   * <p />
   * Contrary to the {@link org.jboss.errai.mvp.client.views.View#setInSlot(Object, com.google.gwt.user.client.ui.Widget)} method, no
   * {@link org.jboss.errai.mvp.client.events.ResetPresentersEvent} is fired,
   * so {@link PresenterWidget#onReset()} is not invoked.
   *
   * @param child The popup child, a {@link PresenterWidget}. Passing {@code null}
   *          will clear the slot.
   * @param center Pass {@code true} to center the popup, otherwise its position
   *          will not be adjusted.
   *
   * @see #addToPopupSlot(PresenterWidget)
   */
  void addToPopupSlot(final PresenterWidget<? extends PopupView> child, boolean center);
}
