/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.places;

import java.util.List;

/**
 * Formats tokens from {@code String} values to {@link PlaceRequest} and {@link PlaceRequest}
 * hierarchies and vice-versa. An implementation is provided in {@link ParameterTokenFormatter}, see
 * the documentation of this class for details.
 */
public interface TokenFormatter {

  /**
   * Converts a place request hierarchy (a list of {@link PlaceRequest}) into a history token. The
   * history token will not be fully query encoded, it may still contain some unescaped separators.
   * However, these separators can be encoded and it will still work, making the scheme robust to
   * systems that force encoding these characters like some email apps.
   *
   * @param placeRequestHierarchy The place request hierarchy.
   * @return The history token, URL encoded.
   * @throws TokenFormatException if there is an error converting.
   */
  String toHistoryToken(List<PlaceRequest> placeRequestHierarchy)
      throws TokenFormatException;

  /**
   * Converts a place token into a {@link PlaceRequest}. A place token is different than a history
   * token, since the history token can contain a hierarchy of different place requests.
   *
   * @param placeToken The place token.
   * @return The place request.
   * @throws TokenFormatException if there is an error converting.
   */
  PlaceRequest toPlaceRequest(String placeToken) throws TokenFormatException;

  /**
   * Converts a history token into a place request hierarchy (a list of {@link PlaceRequest}).
   *
   * @param historyToken The history token.
   * @return The place request hierarchy.
   * @throws TokenFormatException if there is an error converting.
   */
  List<PlaceRequest> toPlaceRequestHierarchy(String historyToken)
      throws TokenFormatException;

  /**
   * Converts a {@link PlaceRequest} into a place token. A place token is different than a history
   * token, since the history token can contain a hierarchy of different place requests. The history
   * token will not be fully query encoded, it may still contain some unescaped separators.
   * However, these separators can be encoded and it will still work, making the scheme robust to
   * systems that force encoding these characters like some email apps.
   *
   * @param placeRequest The place request.
   * @return The place token, URL encoded.
   * @throws TokenFormatException if there is an error converting.
   */
  String toPlaceToken(PlaceRequest placeRequest) throws TokenFormatException;

}
