/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.client.views;

public interface HasUiHandlers<T extends UiHandlers>{
    void setUiHandlers(T uiHandlers);
}
