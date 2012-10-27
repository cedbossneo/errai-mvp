/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.places;

/**
 * @author Philippe Beaudoin
 */
public class PlaceWithGatekeeper extends PlaceImpl {

  private final Gatekeeper gatekeeper;

  public PlaceWithGatekeeper(String nameToken, Gatekeeper gatekeeper) {
    super(nameToken);
    this.gatekeeper = gatekeeper;
  }

  @Override
  public boolean canReveal() {
    return gatekeeper.canReveal();
  }
}
