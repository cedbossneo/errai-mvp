/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.views;

/**
 * The interface for {@link View} classes that is meant to be displayed as a
 * popup, like a GWT {@link com.google.gwt.user.client.ui.PopupPanel} or a
 * {@link com.google.gwt.user.client.ui.DialogBox}.
 *
 * @author Philippe Beaudoin
 */
public interface PopupView extends View {

  /**
   * Make sure the {@link org.jboss.errai.mvp.client.views.PopupView} is centered in the browser's client area.
   * This method should not change the view visibility: if it was hidden (resp.
   * visible) it remains hidden (resp. visible).
   */
  void center();

  /**
   * Make sure the {@link org.jboss.errai.mvp.client.views.PopupView} is hidden. You can call this method
   * directly.
   */
  void hide();

  /**
   * Indicates that the view should automatically hide when a GWTP
   * {@link com.gwtplatform.mvp.client.proxy.NavigationEvent} is fired. This is
   * better than using GWT's
   * {@link com.google.gwt.user.client.ui.PopupPanel#setAutoHideOnHistoryEventsEnabled(boolean)}
   * since the latter will automatically hide the dialog even if navigation is
   * refused through
   * {@link com.gwtplatform.mvp.client.proxy.PlaceManager#setOnLeaveConfirmation(String)}
   * .
   */
  void setAutoHideOnNavigationEventEnabled(boolean autoHide);

  /**
   * Identifies which {@link PopupViewCloseHandler} should be called when this
   * view closed (either automatically or through a call to {@link #hide()}.
   *
   * @param popupViewCloseHandler The {@link PopupViewCloseHandler} or
   *          {@code null} to unregister any handlers.
   */
  void setCloseHandler(PopupViewCloseHandler popupViewCloseHandler);

  /**
   * Reposition the {@link org.jboss.errai.mvp.client.views.PopupView} within the browser's client area. This
   * method should not change the view visibility: if it was hidden (resp.
   * visible) it remains hidden (resp. visible).
   *
   * @param left The left position of the top-left corner (in pixels).
   * @param top The top position of the top-left corner (in pixels).
   */
  void setPosition(int left, int top);

  /**
   * <b>Important!</b> Do not call this directly, instead use
   * {@link PresenterWidget#addToPopupSlot(PresenterWidget)} passing this
   * view's {@link PresenterWidget}.
   * <p/>
   * Make sure the {@link org.jboss.errai.mvp.client.views.PopupView} is visible.
   */
  void show();

}
