/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.proxy;

import org.jboss.errai.mvp.client.places.Place;
import org.jboss.errai.mvp.client.presenters.Presenter;

/**
 * The interface of a {@link Proxy} that is also a {@link Place}.
 *
 * @param <P> The type of the {@link Presenter} associated with this proxy.
 *
 * @author Philippe Beaudoin
 */
public interface ProxyPlace<P extends Presenter<?>> extends Proxy<P>, Place {

  /**
   * Manually reveals a presenter. Only use this method if your presenter is configured
   * to use manual reveal via {@link Presenter#useManualReveal()}. This method should be
   * called following one or more asynchronous server calls in
   * {@link Presenter#prepareFromRequest(PlaceRequest)}.
   * You should manually reveal your presenter exactly once, when all the data needed to use it is available.
   * <p />
   * If you failed to fetch the data or cannot reveal the presenter you must call
   * {@link #manualRevealFailed()} otherwise navigation will be blocked and your application
   * will appear to be frozen.
   * <p />
   * Also consider using {@link ManualRevealCallback}, which will automatically call
   * {@link #manualReveal(Presenter)} upon success and {@link #manualRevealFailed()} upon
   * failure.
   *
   * @see Presenter#useManualReveal()
   * @see #manualRevealFailed()
   *
   * @param presenter The presenter that will be delayed revealed.
   */
  void manualReveal(Presenter<?> presenter);

  /**
   * Cancels manually revealing a presenter. Only use this method if your presenter is configured
   * to use manual reveal via {@link Presenter#useManualReveal()}. For more details see
   * {@link #manualReveal(Presenter)}.
   *
   * @see #manualReveal(Presenter)
   */
  void manualRevealFailed();

}
