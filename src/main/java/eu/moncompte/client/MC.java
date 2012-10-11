package eu.moncompte.client;

import com.google.gwt.core.client.GWT;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.mvp.client.places.PlaceManager;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 9/30/12
 * Time: 11:23 PM
 * To change this template use File | Settings | File Templates.
 */
@EntryPoint
public class MC {

    public static MCMessages Messages = GWT.create(MCMessages.class);

    @Inject
    PlaceManager placeManager;

    @AfterInitialization
    public void startApp() {
        placeManager.fireCurrentPlace();
    }

}
