/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.views;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * This interface represents a tab after it has been instantiated
 * as a {@link com.google.gwt.user.client.ui.Widget}. The full description of a tab before it is
 * created is given by {@link TabData}.
 *
 * @author Philippe Beaudoin
 */
public interface Tab extends HasText {

  /**
   * Should not be called directly. Call {@link TabPanel#setActiveTab(Tab)}
   * instead.
   */
  void activate();

  /**
   * Every tab should be able to return itself as an instance of a widget class.
   *
   * @return The tab as a {@link com.google.gwt.user.client.ui.Widget}.
   */
  Widget asWidget();

  /**
   * Should not be called directly. Call {@link TabPanel#setActiveTab(Tab)}
   * instead.
   */
  void deactivate();

  /**
   * A tab priority indicates where it should appear within the tab strip. In
   * typical implementations of {@link TabPanel}, a tab with low priority will
   * be placed more towards the left of the strip. Two tabs with the same
   * priority will be placed in an arbitrary order.
   *
   * @return The priority.
   */
  float getPriority();

  /**
   * Gets the text displayed on the tab.
   *
   * @return The text.
   *
   * @see com.google.gwt.user.client.ui.HasText#getText()
   */
  @Override
  String getText();

  /**
   * Sets the history token this tab links to.
   *
   * @param historyToken The history token.
   */
  void setTargetHistoryToken(String historyToken);

  /**
   * Sets the text displayed on the tab.
   *
   * @param text The text.
   *
   * @see com.google.gwt.user.client.ui.HasText#setText(String)
   */
  @Override
  void setText(String text);

}