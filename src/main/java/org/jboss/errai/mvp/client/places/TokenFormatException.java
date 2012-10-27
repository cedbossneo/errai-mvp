/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.places;

/**
 * @author Philippe Beaudoin
 */
public final class TokenFormatException extends RuntimeException {
  private static final long serialVersionUID = 3707135366220900675L;

  public TokenFormatException() {
  }

  public TokenFormatException(String message) {
    super(message);
  }

  public TokenFormatException(String message, Throwable cause) {
    super(message, cause);
  }

  public TokenFormatException(Throwable cause) {
    super(cause);
  }

}
