/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation used to specify the title of a place as a string. This title is
 * used when retrieving a place title through
 * {@link org.jboss.errai.mvp.client.places.PlaceManager#getCurrentTitle}. For
 * more control see {@link TitleFunction}.
 *
 * @author Philippe Beaudoin
 */
@Target(ElementType.TYPE)
public @interface Title {
  String value() default "";
}
