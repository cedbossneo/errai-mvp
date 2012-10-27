/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.views;

public interface HasUiHandlers<T extends UiHandlers>{
    void setUiHandlers(T uiHandlers);
}
