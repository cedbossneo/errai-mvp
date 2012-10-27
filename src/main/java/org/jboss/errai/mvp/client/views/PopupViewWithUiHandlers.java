/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.views;

import com.google.web.bindery.event.shared.EventBus;

/**
 * Base class for a {@link PopupView} that implements the {@link HasUiHandlers}
 * interface. You should always call {@link #setUiHandlers(UiHandlers)} from your
 * presenter 's constructor.
 * <p />
 * <b>Important!</b> Never call {@link #getUiHandlers()} inside your constructor
 * since the {@link UiHandlers} are not yet set.
 *
 * @param <C> Your {@link UiHandlers} interface type.
 *
 * @author Christian Goudreau
 * @author Philippe Beaudoin
 */
public abstract class PopupViewWithUiHandlers<C extends UiHandlers> extends
PopupViewImpl implements HasUiHandlers<C> {
  private C uiHandlers;

  /**
   * The {@link org.jboss.errai.mvp.client.views.PopupViewWithUiHandlers} class uses the {@link com.google.web.bindery.event.shared.EventBus} to listen to
   * {@link org.jboss.errai.mvp.client.events.NavigationEvent} in order to automatically
   * close when this event is fired, if desired. See
   * {@link #setAutoHideOnNavigationEventEnabled(boolean)} for details.
   *
   * @param eventBus The {@link com.google.web.bindery.event.shared.EventBus}.
   */
  protected PopupViewWithUiHandlers(EventBus eventBus) {
    super(eventBus);
  }

  /**
   * Access the {@link UiHandlers} associated with this {@link View}.
   * <p>
   * <b>Important!</b> Never call {@link #getUiHandlers()} inside your constructor
   * since the {@link UiHandlers} are not yet set.
   *
   * @return The {@link UiHandlers}, or {@code null} if they are not yet set.
   */
  protected C getUiHandlers() {
    return uiHandlers;
  }

  @Override
  public void setUiHandlers(C uiHandlers) {
    this.uiHandlers = uiHandlers;
  }
}
