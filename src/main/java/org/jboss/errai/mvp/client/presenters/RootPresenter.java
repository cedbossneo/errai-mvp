/*
 * Copyright 2012 Cedric Hauber
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.jboss.errai.mvp.client.presenters;

import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.events.*;
import org.jboss.errai.mvp.client.views.View;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This is the presenter for the top-level of the application. It is derived
 * from presenter widget, but it's just because it doesn't need a proxy has it
 * will be bound as an eager singleton. It sets content within GWT's
 * {@link com.google.gwt.user.client.ui.RootPanel} and {@link com.google.gwt.user.client.ui.RootLayoutPanel}.
 * <p />
 * Fire a {@link RevealRootContentEvent} or {@link RevealRootLayoutContentEvent}
 * to set your presenter at the top level. The
 * choice depends on whether your presenter works as a
 * {@link com.google.gwt.user.client.ui.Panel} or as a
 * {@link com.google.gwt.user.client.ui.LayoutPanel}.
 *
 * @author Philippe Beaudoin
 */
@Singleton
public class RootPresenter extends
    PresenterWidget<RootPresenter.MyView> implements
        ResetPresentersHandler, RevealRootContentHandler,
        RevealRootLayoutContentHandler,
        LockInteractionHandler {

    public static final Object rootSlot = new Object();

  private boolean isResetting;

  /**
   * Creates a proxy class for a presenter that can contain tabs.
   *
   * @param eventBus The event bus.
   */
  @Inject
  public RootPresenter(final EventBus eventBus, final RootView view) {
    super(eventBus, view);
    visible = true;
  }

  @Override
  protected void onBind() {
    super.onBind();

    addRegisteredHandler(ResetPresentersEvent.getType(), this);

    addRegisteredHandler(RevealRootContentEvent.getType(), this);

    addRegisteredHandler(RevealRootLayoutContentEvent.getType(), this);

    addRegisteredHandler(LockInteractionEvent.getType(), this);
  }

  @Override
  public void onResetPresenters(ResetPresentersEvent resetPresentersEvent) {
    if (!isResetting) {
      isResetting = true;
      internalReset();
      isResetting = false;
    }
  }
  @Override
  public void onRevealRootContent(
      final RevealRootContentEvent revealContentEvent) {
    getView().setUsingRootLayoutPanel(false);
    setInSlot(rootSlot, revealContentEvent.getContent());
  }

  public void onRevealRootLayoutContent(
      final RevealRootLayoutContentEvent revealContentEvent) {
    getView().setUsingRootLayoutPanel(true);
    setInSlot(rootSlot, revealContentEvent.getContent());
  }

  @Override
  public void onLockInteraction(LockInteractionEvent lockInteractionEvent) {
    if (lockInteractionEvent.shouldLock()) {
      getView().lockScreen();
    } else {
      getView().unlockScreen();
    }
  }

    public static interface MyView extends View {
        void setUsingRootLayoutPanel(boolean b);

        void lockScreen();

        void unlockScreen();
    }

}
