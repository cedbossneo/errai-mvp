/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.proxy;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.events.ChangeTabEvent;
import org.jboss.errai.mvp.client.events.ChangeTabHandler;
import org.jboss.errai.mvp.client.events.RequestTabsEvent;
import org.jboss.errai.mvp.client.events.RequestTabsHandler;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.views.Tab;
import org.jboss.errai.mvp.client.views.TabData;

/**
 * @param <T> The Presenter's type.
 *
 * @author Philippe Beaudoin
 */
public class NonLeafTabContentProxyImpl<T extends Presenter<?>> extends ProxyImpl<T>
    implements NonLeafTabContentProxy<T> {

  protected TabData tabData;
  protected String targetHistoryToken;
  protected Type<RequestTabsHandler> requestTabsEventType;
  protected Type<ChangeTabHandler> changeTabEventType;

  private Tab tab;

  /**
   * Creates a {@link Proxy} for a {@link Presenter} that is meant to be contained within a
   * {@link org.jboss.errai.mvp.client.presenters.TabContainerPresenter} but not as a leaf. As such, these
   * proxy hold information that can be displayed on the tab together with the target history
   * token of the leaf page to display when the tab is clicked.
   */
    public NonLeafTabContentProxyImpl(Class<T> presenterClass) {
        super(presenterClass);
    }

    @Override
  public TabData getTabData() {
    return tabData;
  }

  @Override
  public String getTargetHistoryToken() {
    return targetHistoryToken;
  }

  @Override
  public Tab getTab() {
    return tab;
  }

  @Override
  public void changeTab(TabData tabData) {
    this.tabData = tabData;
    if (changeTabEventType != null) {
      ChangeTabEvent.fire(this, changeTabEventType, this);
    }
  }

  protected void addRequestTabsHandler() {
    eventBus.addHandler(requestTabsEventType, new RequestTabsHandler() {
      @Override
      public void onRequestTabs(RequestTabsEvent event) {
        tab = event.getTabContainer().addTab(NonLeafTabContentProxyImpl.this);
      }
    });
  }

  @Override
  public void changeTab(TabData tabData, String targetHistoryToken) {
    this.targetHistoryToken = targetHistoryToken;
    changeTab(tabData);
  }
}
