package eu.moncompte.client;

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
 * Heure: 11:11
 */
public class NameTokens {

    public static final String HOME = "home";
    public static final String LOGIN = "login";
    public static final String REGISTER = "register";

    public static String getHomePage() {
        return HOME;
    }

    public static String getLoginPage() {
        return LOGIN;
    }

    public String getRegisterPage() {
        return REGISTER;
    }
}
