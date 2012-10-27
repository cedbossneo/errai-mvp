/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.presenters;


import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Bind this class to indicate that you wish to globally disable automatic binding in
 * classes derived from {@link HandlerContainerImpl}, such as {@link PresenterWidget}
 * or {@link Presenter}. Simply bind it like this:
 * <pre>
 * bind(AutobindDisable.class).toInstance(new AutobindDisable(true));
 * </pre>
 * If you do not bind this class then autobinding is controlled on a case-by-case
 * basis using the {@link HandlerContainerImpl}'s constructors.
 * <p />
 * Disabling automatic binding can be useful in unit tests, for example.
 *
 * @author Philippe Beaudoin
 */
@Singleton
public class AutobindDisable {
  private final boolean disable;

  @Inject
  public AutobindDisable() {
    disable = false;
  }

  public AutobindDisable(boolean disable) {
    this.disable = disable;
  }

  public boolean disable() {
    return disable;
  }
}
