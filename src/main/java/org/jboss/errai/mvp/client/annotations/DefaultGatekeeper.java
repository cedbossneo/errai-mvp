/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Use this annotation in your custom ginjector to annotate a
 * {@link org.jboss.errai.mvp.client.places.Gatekeeper}-derived class. This class
 * will be used to provide places for proxies that are not annotated with the
 * {@link UseGatekeeper} annotation.
 *
 * @author Philippe Beaudoin
 */
@Target(ElementType.TYPE)
public @interface DefaultGatekeeper {
}
