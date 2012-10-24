/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.mvp.client.events.RevealRootContentEvent;
import org.jboss.errai.mvp.client.events.RevealRootContentHandler;
import org.jboss.errai.mvp.client.proxy.ProxyManager;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MVP {
    @Inject
    private ProxyManager manager;

    @Inject
    private EventBus eventBus;

    @PostConstruct
    public void bindEvents(){
        eventBus.addHandler(RevealRootContentEvent.getType(), new RevealRootContentHandler() {
            @Override
            public void onRevealRootContent(RevealRootContentEvent event) {
                RootPanel rootPanel = RootPanel.get();
                rootPanel.clear();
                rootPanel.add(event.getContent().getWidget());
            }
        });
    }
}
