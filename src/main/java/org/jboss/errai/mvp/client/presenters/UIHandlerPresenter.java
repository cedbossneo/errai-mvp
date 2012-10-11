/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.client.presenters;

import org.jboss.errai.mvp.client.views.HasUiHandlers;
import org.jboss.errai.mvp.client.views.UiHandlers;
import org.jboss.errai.mvp.client.views.View;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 9/30/12
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class UIHandlerPresenter<V extends View & HasUiHandlers> extends Presenter<V> implements UiHandlers {
    public UIHandlerPresenter(V view){
        super(view);
        getView().setUiHandlers(this);
    }
}