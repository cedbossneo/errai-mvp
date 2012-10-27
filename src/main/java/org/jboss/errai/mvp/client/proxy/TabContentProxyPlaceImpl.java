/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.proxy;

import org.jboss.errai.mvp.client.places.Place;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.views.Tab;
import org.jboss.errai.mvp.client.views.TabData;

/**
 * A useful mixing class to define a {@link TabContentProxy} that is also a
 * {@link org.jboss.errai.mvp.client.places.Place}.
 *
 * @param <T> The Presenter's type.
 *
 * @author Philippe Beaudoin
 */
public class TabContentProxyPlaceImpl<T extends Presenter<?>> extends
    ProxyPlaceAbstract<T, TabContentProxy<T>> implements TabContentProxyPlace<T> {

    /**
     * Creates a {@link org.jboss.errai.mvp.client.proxy.ProxyPlaceAbstract}. That is, the {@link org.jboss.errai.mvp.client.proxy.Proxy} of a
     * {@link org.jboss.errai.mvp.client.presenters.Presenter} attached to a {@link org.jboss.errai.mvp.client.places.Place}. This presenter can be
     * invoked by setting a history token that matches its name token in the URL
     * bar.
     */
    public TabContentProxyPlaceImpl(TabContentProxy<T> proxy, Place place) {
        super(proxy, place);
    }

    @Override
  public String getTargetHistoryToken() {
    return getNameToken();
  }

  @Override
  public TabData getTabData() {
    return proxy.getTabData();
  }

  @Override
  public Tab getTab() {
    return proxy.getTab();
  }

  @Override
  public void changeTab(TabData tabData) {
    proxy.changeTab(tabData);
  }
}
