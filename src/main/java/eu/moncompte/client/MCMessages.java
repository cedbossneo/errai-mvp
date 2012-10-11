package eu.moncompte.client;

import com.google.gwt.i18n.client.Messages;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 9/30/12
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MCMessages extends Messages {
    String sendButton();

    String welcome();

    String connect();

    String login();

    String password();

    String name();

    String email();

    String phoneNumber();

    String register();

    String loginIncorrect();

    String passwordIncorrect();

    String save();

    String reset();

    String newItem();

    String remove();

    String errorRemove();

    String errorUpdate();

    String errorRegister();

    String errorLogin();

    String onLogin(String p0);

    String errorNew();
}
