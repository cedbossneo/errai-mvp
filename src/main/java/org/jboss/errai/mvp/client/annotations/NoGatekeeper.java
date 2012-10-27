/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * This annotation lets you specify that the {@link org.jboss.errai.mvp.client.places.Place}
 * associated with your proxy should not use a {@link org.jboss.errai.mvp.client.places.Gatekeeper}
 * even if one is defined with {@link DefaultGatekeeper}.
 *
 * @author Philippe Beaudoin
 */
@Target(ElementType.TYPE)
public @interface NoGatekeeper {
}
