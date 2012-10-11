/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 10/3/12
 * Time: 8:23 PM
 * To change this template use File | Settings | File Templates.
 */
@Target( {ElementType.METHOD})
@Retention(RUNTIME)
public @interface ProxyEvent {
}
