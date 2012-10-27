/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.annotations;

import org.jboss.errai.mvp.client.places.Gatekeeper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * This annotation lets you define a {@link Gatekeeper} to use for the
 * {@link org.jboss.errai.mvp.client.places.Place} associated with
 * your proxy. Your custom {@code Ginjector} must
 * have a method returning the {@link Gatekeeper} specified in this annotation.
 *
 * @author Olivier Monaco
 * @author Philippe Beaudoin
 */
@Target(ElementType.TYPE)
public @interface UseGatekeeper {
  Class<? extends Gatekeeper> value();
}
