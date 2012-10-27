/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * This annotation can be used to specify a function returning the title of a
 * place as a string, given the request. It can work in one of two ways. For
 * simple hard-coded titles see {@link Title}.
 * <p />
 * 1) You can use it to annotate a static or non-static public method in your
 * presenter that returns a string (the title). This method can optionally
 * accept a {@link org.jboss.errai.mvp.client.places.PlaceRequest} parameter to
 * contain the place request for which the title is desired. It can also
 * optionally accept another parameter corresponding to your custom ginjector.
 * Using a static method is more efficient, as it doesn't force instantiation of
 * the associated presenter and view. Example of use:
 *
 * <pre>
 * {@code @}TitleFunction
 *  static public String getTranslatedTitle( MyGinjector ginjector ) {
 *    return ginjector.getTranslations().productTitle();
 *  }
 *
 * {@code @}TitleFunction
 *  public String getTitle( PlaceRequest placeRequest ) {
 *    prepareFromRequest( placeRequest );
 *    return "Email #" + getEmailId();
 *  }
 * </pre>
 * <p />
 * 2) You can use it to annotate a static or non-static public method in your
 * presenter that returns {@code void} but accept a parameter of type
 * {@link org.jboss.errai.mvp.client.events.SetPlaceTitleHandler}. In this case,
 * your method is responsible for calling this handler's {@code onSetPlaceTitle}
 * with the title for this place, or {@code null} if the place doesn't have a
 * title. This is useful if the title can only be accessed in an asynchronous
 * fashion, for example following a call to the server. As above, your method
 * can accept a {@link org.jboss.errai.mvp.client.places.PlaceRequest} parameter
 * and your custom ginjector. Example of use:
 *
 * <pre>
 * {@code @}TitleFunction
 *  public void getTitle( PlaceRequest placeRequest, final SetPlaceTitleHandler handler ) {
 *    prepareFromRequest( placeRequest );
 *    dispatcher.execute( new GetUserNameAction( getUserId(), new AsyncCallback<GetUserNameResult>(){
 *      public void onSuccess(GetUserNameResult result) {
 *        handler.onSetPlaceTitle( result.getUserName() );
 *      }
 *      public void onFailure(Throwable caught) {
 *        handler.onSetPlaceTitle(null);
 *      }
 *    }
 *  }
 * </pre>
 *
 * @author Philippe Beaudoin
 */
@Target(ElementType.METHOD)
public @interface TitleFunction {
}
