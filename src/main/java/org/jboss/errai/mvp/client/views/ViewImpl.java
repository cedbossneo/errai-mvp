/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.views;

import com.google.gwt.user.client.ui.Widget;

/**
 * A simple implementation of {@link View} that simply disregards every call to
 * {@link #setInSlot(Object, com.google.gwt.user.client.ui.Widget)}, {@link #addToSlot(Object, com.google.gwt.user.client.ui.Widget)}, and
 * {@link #removeFromSlot(Object, com.google.gwt.user.client.ui.Widget)}.
 * <p />
 * Feel free not to inherit from this if you need another base class (such as
 * {@link com.google.gwt.user.client.ui.Composite}), but you will have to define
 * the above methods.
 *
 * @author Philippe Beaudoin
 * @author Christian Goudreau
 */
public abstract class ViewImpl implements View {

  @Override
  public void addToSlot(Object slot, Widget content) {
  }

  @Override
  public void removeFromSlot(Object slot, Widget content) {
  }

  @Override
  public void setInSlot(Object slot, Widget content) {
  }
}
