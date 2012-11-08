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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.errai.mvp.client.views.ViewImpl;

/**
 * {@link RootPresenter}'s view.
 */
public class RootView extends ViewImpl implements RootPresenter.MyView {

  private boolean usingRootLayoutPanel;

  /**
   * The glass element.
   */
  private Element glass;

  @Override
  public Widget asWidget() {
    assert false : "Root view has no widget, you should never call asWidget()";
    return null;
  }

  @Override
  public void setInSlot(Object slot, Widget content) {
    assert slot == RootPresenter.rootSlot : "Unknown slot used in the root proxy.";

    if (usingRootLayoutPanel) {
      // TODO Next 3 lines are a dirty workaround for
      // http://code.google.com/p/google-web-toolkit/issues/detail?id=4737
      RootPanel.get().clear();
      RootLayoutPanel.get().clear();
      RootPanel.get().add(RootLayoutPanel.get());
      if (content != null) {
        RootLayoutPanel.get().add(content);
      }
    } else {
      // TODO Next 2 lines are a dirty workaround for
      // http://code.google.com/p/google-web-toolkit/issues/detail?id=4737
      RootLayoutPanel.get().clear();
      RootPanel.get().clear();
      if (content != null) {
        RootPanel.get().add(content);
      }
    }
  }

  public void setUsingRootLayoutPanel(boolean usingRootLayoutPanel) {
    this.usingRootLayoutPanel = usingRootLayoutPanel;
  }

  public void lockScreen() {
    ensureGlass();
    Document.get().getBody().appendChild(glass);
  }

  public void unlockScreen() {
    ensureGlass();
    Document.get().getBody().removeChild(glass);
  }

  public void ensureGlass() {
    if (glass == null) {
      glass = Document.get().createDivElement();

      Style style = glass.getStyle();
      style.setPosition(Style.Position.ABSOLUTE);
      style.setLeft(0, Style.Unit.PX);
      style.setTop(0, Style.Unit.PX);
      style.setRight(0, Style.Unit.PX);
      style.setBottom(0, Style.Unit.PX);
      style.setZIndex(2147483647); // Maximum z-index
    }
  }
}
