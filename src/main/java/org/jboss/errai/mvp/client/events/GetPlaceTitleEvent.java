/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import org.jboss.errai.mvp.client.places.PlaceRequest;

/**
 * This event is fired whenever the user wants to have access to the title of a
 * place.
 * <p />
 * <b>Important!</b> You should never fire that event directly. Instead, use
 * {@link PlaceManager#getCurrentTitle(SetPlaceTitleHandler)} or
 * {@link PlaceManager#getTitle(int, SetPlaceTitleHandler)}.
 *
 * @author Philippe Beaudoin
 */
public class GetPlaceTitleEvent extends GwtEvent<GetPlaceTitleHandler> {

  private static Type<GetPlaceTitleHandler> TYPE;

  /**
   * Fires a {@link org.jboss.errai.mvp.client.events.GetPlaceTitleEvent}
   * into a source that has access to an {@link com.google.web.bindery.event.shared.EventBus}.
   * <p />
   * <b>Important!</b> You should never fire that event directly. See
   * {@link org.jboss.errai.mvp.client.events.GetPlaceTitleEvent} for details.
   *
   * @param source The source that fires this event ({@link com.google.gwt.event.shared.HasHandlers}).
   * @param request The {@link PlaceRequest} for which to obtain the title.
   * @param handler The {@link SetPlaceTitleHandler} that will be invoked when
   *          the title is obtained.
   */
  public static void fire(HasHandlers source, PlaceRequest request,
      SetPlaceTitleHandler handler) {
    source.fireEvent(new GetPlaceTitleEvent(request, handler));
  }

  public static Type<GetPlaceTitleHandler> getType() {
    if (TYPE == null) {
      TYPE = new Type<GetPlaceTitleHandler>();
    }
    return TYPE;
  }

  /**
   * The handled flag can let others know when the event has been handled.
   * Handlers should call {@link #setHandled()} as soon as they figure they are
   * be responsible for this event. Handlers should not process this event if
   * {@link #isHandled()} return {@code true}.
   */
  private boolean handled;

  private final SetPlaceTitleHandler handler;

  private final PlaceRequest request;

  public GetPlaceTitleEvent(PlaceRequest request, SetPlaceTitleHandler handler) {
    this.request = request;
    this.handler = handler;
  }

  @Override
  public Type<GetPlaceTitleHandler> getAssociatedType() {
    return getType();
  }

  public SetPlaceTitleHandler getHandler() {
    return handler;
  }

  public PlaceRequest getRequest() {
    return request;
  }

  /**
   * Checks if the event was handled. If it was, then it should not be processed
   * further.
   *
   * @return {@code true} if the event was handled. {@code false} otherwise.
   */
  public boolean isHandled() {
    return handled;
  }

  /**
   * Indicates that the event was handled and that other contentHandlers should not
   * process it.
   */
  public void setHandled() {
    handled = true;
  }

  @Override
  protected void dispatch(GetPlaceTitleHandler handler) {
    handler.onGetPlaceTitle(this);
  }
}
