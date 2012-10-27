/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import org.jboss.errai.mvp.client.proxy.TabContentProxy;

/**
 * This event is fired whenever a tab contained in a {@link org.jboss.errai.mvp.client.presenters.TabContainerPresenter} wants to change
 * its information.
 *
 * @author Philippe Beaudoin
 */
public final class ChangeTabEvent extends GwtEvent<ChangeTabHandler> {

  /**
   * Fires a {@link ChangeTabEvent} with a specific
   * {@link com.google.gwt.event.shared.GwtEvent.Type} into a source that has access to an
   * {@link com.google.web.bindery.event.shared.EventBus}.
   *
   * @param source The source that fires this event ({@link com.google.gwt.event.shared.HasHandlers}).
   * @param type The specific event {@link com.google.gwt.event.shared.GwtEvent.Type}.
   * @param tabContentProxy The {@link org.jboss.errai.mvp.client.proxy.TabContentProxy} for this tab, with modified information.
   */
  public static void fire(final HasHandlers source,
      final Type<ChangeTabHandler> type, TabContentProxy<?> tabContentProxy) {

    source.fireEvent(new ChangeTabEvent(type, tabContentProxy));
  }

  private final TabContentProxy<?> tabContentProxy;
  private final Type<ChangeTabHandler> type;

  /**
   * Creates an event for requesting the modification to a tab displayed in a
   * {@link org.jboss.errai.mvp.client.presenters.TabContainerPresenter}.
   *
   * @param type The specific {@link com.google.gwt.event.shared.GwtEvent.Type} of this event.
   * @param tabContentProxy The {@link TabContentProxy} for this tab, with modified information.
   */
  public ChangeTabEvent(final Type<ChangeTabHandler> type,
      TabContentProxy<?> tabContentProxy) {
    this.type = type;
    this.tabContentProxy = tabContentProxy;
  }

  @Override
  public Type<ChangeTabHandler> getAssociatedType() {
    return type;
  }

  public TabContentProxy<?> getTabContentProxy() {
    return tabContentProxy;
  }

  @Override
  protected void dispatch(ChangeTabHandler handler) {
    handler.onChangeTab(this);
  }

}
