/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.views;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.jboss.errai.mvp.client.events.NavigationEvent;
import org.jboss.errai.mvp.client.events.NavigationHandler;

/**
 * A simple implementation of {@link PopupView} that can be used when the widget
 * returned by {@link #asWidget()} inherits from {@link com.google.gwt.user.client.ui.PopupPanel}.
 *
 * Also, this implementation simply disregards every call to
 * {@link #setInSlot(Object, com.google.gwt.user.client.ui.Widget)}, {@link #addToSlot(Object, com.google.gwt.user.client.ui.Widget)}, and
 * {@link #removeFromSlot(Object, com.google.gwt.user.client.ui.Widget)}.
 *
 * @author Philippe Beaudoin
 */
public abstract class PopupViewImpl extends ViewImpl implements PopupView {

  private HandlerRegistration autoHideHandler;

  private HandlerRegistration closeHandlerRegistration;
  private final EventBus eventBus;

  /**
   * The {@link org.jboss.errai.mvp.client.views.PopupViewImpl} class uses the {@link com.google.web.bindery.event.shared.EventBus} to listen to
   * {@link NavigationEvent} in order to automatically close when this event is
   * fired, if desired. See
   * {@link #setAutoHideOnNavigationEventEnabled(boolean)} for details.
   *
   * @param eventBus The {@link com.google.web.bindery.event.shared.EventBus}.
   */
  protected PopupViewImpl(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  @Override
  public void center() {
    doCenter();
    // We center again in a deferred command to solve a bug in IE where newly
    // created window are sometimes not centered.
    Scheduler.get().scheduleDeferred(new Command() {
      @Override
      public void execute() {
        doCenter();
      }
    });
  }

  @Override
  public void hide() {
    asPopupPanel().hide();
  }

  @Override
  public void setAutoHideOnNavigationEventEnabled(boolean autoHide) {
    if (autoHide) {
      if (autoHideHandler != null) {
        return;
      }
      autoHideHandler = eventBus.addHandler(NavigationEvent.getType(),
          new NavigationHandler() {
            @Override
            public void onNavigation(NavigationEvent navigationEvent) {
              hide();
            }
          });
    } else {
      if (autoHideHandler != null) {
        autoHideHandler.removeHandler();
      }
    }
  }

  @Override
  public void setCloseHandler(final PopupViewCloseHandler popupViewCloseHandler) {
    if (closeHandlerRegistration != null) {
      closeHandlerRegistration.removeHandler();
    }
    if (popupViewCloseHandler == null) {
      closeHandlerRegistration = null;
    } else {
      closeHandlerRegistration = asPopupPanel().addCloseHandler(
          new CloseHandler<PopupPanel>() {
            @Override
            public void onClose(CloseEvent<PopupPanel> event) {
              popupViewCloseHandler.onClose();
            }
          });
    }
  }

  @Override
  public void setPosition(int left, int top) {
    asPopupPanel().setPopupPosition(left, top);
  }

  @Override
  public void show() {
    asPopupPanel().show();
  }

  /**
   * Retrieves this view as a {@link com.google.gwt.user.client.ui.PopupPanel}. See {@link #asWidget()}.
   *
   * @return This view as a {@link com.google.gwt.user.client.ui.PopupPanel} object.
   */
  protected PopupPanel asPopupPanel() {
    return (PopupPanel) asWidget();
  }

  /**
   * This method centers the popup panel, temporarily making it visible if
   * needed.
   */
  private void doCenter() {
    boolean wasVisible = asPopupPanel().isShowing();
    asPopupPanel().center();
    if (!wasVisible) {
      asPopupPanel().hide();
    }
  }
}
