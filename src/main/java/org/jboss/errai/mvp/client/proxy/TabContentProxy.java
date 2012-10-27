/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.proxy;

import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.views.Tab;
import org.jboss.errai.mvp.client.views.TabData;

/**
 * The interface for the {@link Proxy} of a {@link Presenter} that can
 * be displayed within a
 * {@link org.jboss.errai.mvp.client.presenters.TabContainerPresenter TabContainerPresenter}'s main area.
 * You should not use this proxy directly. If the presenter is associated to a name token use
 * {@link TabContentProxyPlace} instead. If the presenter is not a leaf, so it is not associated
 * with a name token, use {@link NonLeafTabContentProxy} instead.
 *
 * @see com.gwtplatform.mvp.client.annotations.TabInfo TabInfo
 *
 * @param <P> The type of the {@link Presenter} associated with this proxy.
 *
 * @author Philippe Beaudoin
 */
public interface TabContentProxy<P extends Presenter<?>> extends Proxy<P> {
  /**
   * Retrieves the {@link TabData} that should be used to create this tab.
   *
   * @return The tab data.
   */
  TabData getTabData();

  /**
   * Gets the history token that should be accessed when the tab is clicked.
   * In the fairly typical scenario where a tab directly contains a place,
   * this should return the name token of that place. In the case of tabs
   * that contain non-leaf presenters (for example, other tabs), this should
   * return the name token of a leaf-level presenter. See {@link NonLeafTabContentProxy}.
   *
   * @return The history token.
   */
  String getTargetHistoryToken();

  /**
   * Retrieves the {@link Tab} object that was created from the
   * {@link TabData} returned by {@link #getTabData()}.
   *
   * @return The tab.
   */
  Tab getTab();

  /**
   * Changes the data associated with this tab. This will automatically cause the displayed tab to
   * change, provided the
   * {@link org.jboss.errai.mvp.client.presenters.TabContainerPresenter TabContainerPresenter} containing this
   * tab defines a {@link com.gwtplatform.mvp.client.annotations.ChangeTab ChangeTab} field and
   * passes it to the parent constructor.
   */
  void changeTab(TabData tabData);
}
