/*
 * Copyright (c) 2012.
 * Cedric Hauber
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
