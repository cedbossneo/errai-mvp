/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.views;

/**
 * Classes of this type contains all the data required to create
 * a new {@link Tab}. The default implementation is
 * {@link TabDataBasic} but you can create your own class, with
 * more information, if desired. See
 * {@link com.gwtplatform.mvp.client.annotations.TabInfo}
 * for more details.
 *
 * @author Philippe Beaudoin
 */
public interface TabData {

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
   * Gets the label to display on the tab.
   *
   * @return The label.
   */
  String getLabel();
}
