/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.views;

/**
 * The basic type of {@link TabData}, with just enough information to create
 * a basic {@link Tab}.
 *
 * @author Philippe Beaudoin
 */
public class TabDataBasic implements TabData {

  private final String label;
  private final float priority;

  public TabDataBasic(String label, float priority) {
    this.label = label;
    this.priority = priority;
  }

  @Override
  public String getLabel() {
    return label;
  }

  @Override
  public float getPriority() {
    return priority;
  }
}
