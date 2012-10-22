/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.client.annotations;

import org.jboss.errai.ioc.client.api.LoadAsync;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( {ElementType.TYPE})
@Retention(RUNTIME)
public @interface NameToken {
    String value();
}
