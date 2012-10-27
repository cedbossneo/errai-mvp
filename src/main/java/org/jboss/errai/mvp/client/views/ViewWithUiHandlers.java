/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.views;

public abstract class ViewWithUiHandlers<C extends UiHandlers> extends ViewImpl
        implements HasUiHandlers<C> {
    private C uiHandlers;

    protected C getUiHandlers() {
        return uiHandlers;
    }

    public void setUiHandlers(C uiHandlers) {
        this.uiHandlers = uiHandlers;
    }
}
