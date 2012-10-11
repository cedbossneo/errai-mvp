package eu.moncompte.shared.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.web.bindery.event.shared.Event;

/**
 * Application Logicielle Visitors-Book
 * <p/>
 * Copyright (c) : Jade-i, 2010-2012, All rights reserved.
 * <p/>
 * IDDN.FR.001.500049.000.S.P.2011.000.20700
 * <p/>
 * Auteur : Laurent Vieille, Nicolas Mallot-Touzet, Bernard Wappler, Cedric Hauber, Joel Kinding-Kinding, Paul Duncan
 * <p/>
 * Dernière modification
 * Utilisateur: cedric
 * Date: 03/10/12
 * Heure: 10:52
 */
public interface LogonUserEventHandler extends EventHandler{
    Event.Type<LogonUserEventHandler> TYPE = new Event.Type<LogonUserEventHandler>();

    public void onUserLogged(LogonUserEvent event);
}
