/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.events.LazyEventBus;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.mvp.client.events.RevealRootContentEvent;
import org.jboss.errai.mvp.client.events.RevealRootContentHandler;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class MVP {
    @Inject
    private IOCBeanManager manager;

    private EventBus eventBus;

    @PostConstruct
    public void bindEvents(){
        produceEventBus().addHandler(RevealRootContentEvent.getType(), new RevealRootContentHandler() {
            @Override
            public void onRevealRootContent(RevealRootContentEvent event) {
                RootPanel rootPanel = RootPanel.get();
                rootPanel.clear();
                rootPanel.add(event.getContent().getWidget());
            }
        });
    }

    @Produces
    private EventBus produceEventBus() {
        if (eventBus == null){
            eventBus = GWT.create(LazyEventBus.class);
            ((LazyEventBus)eventBus).setManager(manager);
        }
        return eventBus;
    }
}
