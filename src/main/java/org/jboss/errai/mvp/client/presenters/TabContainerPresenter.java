/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.jboss.errai.mvp.client.presenters;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.events.ChangeTabEvent;
import org.jboss.errai.mvp.client.events.ChangeTabHandler;
import org.jboss.errai.mvp.client.events.RequestTabsEvent;
import org.jboss.errai.mvp.client.events.RequestTabsHandler;
import org.jboss.errai.mvp.client.proxy.ProxyManager;
import org.jboss.errai.mvp.client.proxy.TabContentProxy;
import org.jboss.errai.mvp.client.views.Tab;
import org.jboss.errai.mvp.client.views.TabPanel;
import org.jboss.errai.mvp.client.views.View;

/**
 * A {@link Presenter} that can display many tabs and the content of one of these tabs.
 * Note that this presenter is only meant to be used when the content are themselves
 * {@link Presenter}, if they are {@link org.jboss.errai.mvp.client.presenters.PresenterWidget} then no special mechanism is
 * required and you can rely on a standard widget.
 * <p />
 * Classes extending {@link TabContainerPresenter} must declare one constant of type
 * {@link com.google.gwt.event.shared.GwtEvent.Type Type&lt;RequestTabsHandler&gt;}
 * and annotate it with {@link com.gwtplatform.mvp.client.annotations.RequestTabs RequestTabs}.
 * For example:
 * <pre>
 *{@literal @}RequestTabs
 * public static final Type&lt;RequestTabsHandler&gt; TYPE_RequestTabs =
 *   new Type&lt;RequestTabsHandler&gt;();
 * </pre>
 * {@link Presenter} meant to appear within a {@link TabContainerPresenter} main
 * content area should be associated to a
 * {@link org.jboss.errai.mvp.client.proxy.TabContentProxy TabContentProxy}
 * or a {@link org.jboss.errai.mvp.client.proxy.TabContentProxyPlace TabContentProxyPlace}.
 *
 * @param <V> The specific type of the {@link org.jboss.errai.mvp.client.views.View}. Must implement {@link org.jboss.errai.mvp.client.views.TabPanel}.
 *
 * @author Philippe Beaudoin
 * @author Christian Goudreau
 */
public abstract class TabContainerPresenter<V extends View & TabPanel>
    extends Presenter<V> {
  private final Type<RequestTabsHandler> requestTabsEventType;
  private final Object tabContentSlot;

  /**
   * Creates a {@link TabContainerPresenter} that uses automatic binding. This will
   * only work when instantiating this object using Guice/GIN dependency injection.
   * See {@link org.jboss.errai.mvp.client.presenters.HandlerContainerImpl#HandlerContainerImpl()} for more details on
   * automatic binding.
   *
   * @param eventBus The {@link com.google.web.bindery.event.shared.EventBus}.
   * @param view The {@link View}.
   * @param tabContentSlot An opaque object identifying the slot in which the
   *          main content should be displayed.
   * @param requestTabsEventType The {@link com.google.gwt.event.shared.GwtEvent.Type} of the object to fire to
   *          identify all the displayed tabs.
   */
  public TabContainerPresenter(EventBus eventBus, V view,
      Object tabContentSlot, Type<RequestTabsHandler> requestTabsEventType,
      Type<ChangeTabHandler> changeTabType) {
    super(eventBus, view);
    this.tabContentSlot = tabContentSlot;
    this.requestTabsEventType = requestTabsEventType;
    if (changeTabType != null) {
      eventBus.addHandler(changeTabType, new ChangeTabHandler() {
        @Override
        public void onChangeTab(ChangeTabEvent event) {
          TabContentProxy<?> tabProxy = event.getTabContentProxy();
          getView().changeTab(tabProxy.getTab(), tabProxy.getTabData(),
              tabProxy.getTargetHistoryToken());
        }
      });
    }
  }

  /**
   * Creates a {@link TabContainerPresenter} that uses automatic binding. This will only work when
   * instantiating this object using Guice/GIN dependency injection. See
   * {@link HandlerContainerImpl#HandlerContainerImpl()} for more details on automatic binding.
   * This version of the constructor does not allow dynamically modifying the tabs after they were
   * created.
   *
   * @param eventBus The {@link com.google.web.bindery.event.shared.EventBus}.
   * @param view The {@link View}.
   * @param proxy The {@link Proxy}.
   * @param tabContentSlot An opaque object identifying the slot in which the
   *          main content should be displayed.
   * @param requestTabsEventType The {@link com.google.gwt.event.shared.GwtEvent.Type} of the object to fire to
   *          identify all the displayed tabs.
   */
  public TabContainerPresenter(EventBus eventBus, V view,
      Object tabContentSlot, Type<RequestTabsHandler> requestTabsEventType) {
    this(eventBus, view, tabContentSlot, requestTabsEventType, null);
  }

  /**
   * Adds a new tab to this presenter. This is meant to be called by a
   * {@link TabContentProxy} in response to a {@link RequestTabsEvent}.
   *
   * @param tabProxy The {@link TabContentProxy} containing information on the tab to add.
   * @return The newly added {@link org.jboss.errai.mvp.client.views.Tab}.
   */
  public Tab addTab(final TabContentProxy<?> tabProxy) {
    return getView().addTab(tabProxy.getTabData(), tabProxy.getTargetHistoryToken());
  }

  @Override
  public void setInSlot(Object slot, PresenterWidget<?> content) {
    super.setInSlot(slot, content);

    // TODO: Consider switching this to an event bus based mechanism where the
    // child presenter fires an event when it is revealed and the parent highlights the tab.

    // If we're setting a presenter attached to an actual slot, then highlight the tab
    if (slot == tabContentSlot) {
      try {
        Presenter<?> presenter = (Presenter<?>) content;
        TabContentProxy<?> proxy = (TabContentProxy<?>) ProxyManager.getPresenterProxy(presenter.getClass());
        getView().setActiveTab(proxy.getTab());
      } catch (Exception e) {
        // We can't cast, no worry, we just won't highlight a tab
      }
    }
  }

  @Override
  protected void onBind() {
    super.onBind();

    // The following call will trigger a series of call to addTab, so
    // we should make sure we clear all the tabs when unbinding.
    RequestTabsEvent.fire(this, requestTabsEventType, this);
  }

  @Override
  protected void onUnbind() {
    super.onUnbind();

    // The tabs are added indirectly in onBind() via the RequestTabsEvent, so we
    // clear them now.
    getView().removeTabs();
  }
}
