/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.proxy;

import org.jboss.errai.mvp.client.presenters.Presenter;

/**
 * The interface for the {@link Proxy} of a {@link Presenter} that has
 * a name token and can be displayed within a
 * {@link com.gwtplatform.mvp.client.TabContainerPresenter TabContainerPresenter}'s main area.
 * If the presenter is not associated to a name token use {@link NonLeafTabContentProxy} instead.
 *
 * Example of use:
 * <pre>
 *{@literal @}ProxyCodeSplit
 *{@literal @}NameToken("homepage")
 *{@literal @}TabInfo(container = MainPagePresenter.class, priority = 0, label = "Home")
 * public interface MyProxy extends TabContentProxyPlace&lt;HomePagePresenter&gt; { }
 * </pre>
 * @see com.gwtplatform.mvp.client.annotations.TabInfo TabInfo
 *
 * @param <P> The type of the {@link Presenter} associated with this proxy.
 *
 * @author Philippe Beaudoin
 */
public interface TabContentProxyPlace<P extends Presenter<?>> extends
    TabContentProxy<P>, ProxyPlace<P> {
}
