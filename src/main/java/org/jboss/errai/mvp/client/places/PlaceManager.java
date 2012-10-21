/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.client.places;


import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class PlaceManager implements ValueChangeHandler<String> {
    @Inject
    private IOCBeanManager manager;
    private final RootPanel container;

    static final Map<String, Class<? extends Presenter>> places = new HashMap<String, Class<? extends Presenter>>();

    public PlaceManager() {
        container = RootPanel.get();
        History.addValueChangeHandler(this);
    }

    public static void registerPlace(String name, Class<? extends Presenter> presenter){
           places.put(name, presenter);
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();
        if (token != null) {
            Presenter presenter = null;
            if (places.containsKey(token)){
                IOCBeanDef<? extends Presenter> bean = manager.lookupBean(places.get(token));
                if (bean != null) {
                    presenter = bean.getInstance();
                }
            }
           /* if (presenter != null) {
                presenter.prepareFromRequest(new PlaceRequest(token));
                presenter.go(container);
                presenter.onReveal();
            }*/
        }
    }

    public void fireCurrentPlace() {
        History.fireCurrentHistoryState();
    }

    public void revealPlace(PlaceRequest placeRequest) {
        History.newItem(placeRequest.toString());
    }
}
