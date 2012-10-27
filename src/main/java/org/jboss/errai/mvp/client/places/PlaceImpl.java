/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.places;

/**
 * @author Philippe Beaudoin
 */
public class PlaceImpl implements Place {

  private final String nameToken;

  public PlaceImpl(String nameToken) {
    this.nameToken = nameToken;
  }

  @Override
  public boolean canReveal() {
    return true;
  }

  @Override
  public final boolean equals(Object o) {
    if (o instanceof Place) {
      Place place = (Place) o;
      return getNameToken().equals(place.getNameToken());
    }
    return false;
  }

  @Override
  public String getNameToken() {
    return nameToken;
  }

  @Override
  public final int hashCode() {
    return 17 * getNameToken().hashCode();
  }

  @Override
  public final boolean matchesRequest(PlaceRequest request) {
    return request.matchesNameToken(getNameToken());
  }

  @Override
  public final String toString() {
    return getNameToken();
  }

}
