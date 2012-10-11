/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.client.presenters;


import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.errai.mvp.client.places.PlaceRequest;
import org.jboss.errai.mvp.client.views.View;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 9/30/12
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Presenter<V extends View> {
    private V view;

    public Presenter(V view){
        this.view = view;
    }

    public void go(final HasWidgets container){
        container.clear();
        container.add(view.asWidget());
    }

    public V getView() {
        return view;
    }

    public void prepareFromRequest(PlaceRequest placeRequest) {
        //To change body of created methods use File | Settings | File Templates.
    }
}