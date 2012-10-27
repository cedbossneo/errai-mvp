/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.views;

/**
 * Classes of this type hold a list of {@link Tab}, from which
 * only one can be active at any time.
 *
 * @author Philippe Beaudoin
 */
public interface TabPanel {

  /**
   * Adds a new tab to the widget.
   *
   * @param tabData The data to use for that tab.
   * @param historyToken The history token the tab points to.
   * @return The newly added {@link Tab}.
   */
  Tab addTab(TabData tabData, String historyToken);

  /**
   * Removes a tab from the widget.
   *
   * @param tab The tab to remove.
   */
  void removeTab(Tab tab);

  /**
   * Removes all tabs from the widget.
   */
  void removeTabs();

  /**
   * Sets the currently active tab.
   *
   * @param tab The tab to activate.
   */
  void setActiveTab(Tab tab);

  /**
   * Change the data or history token associated with a tab.
   *
   * @param tab The tab to change.
   * @param tabData The data to set for this tab.
   * @param historyToken The history token to use for this tab.
   */
  void changeTab(Tab tab, TabData tabData, String historyToken);
}
