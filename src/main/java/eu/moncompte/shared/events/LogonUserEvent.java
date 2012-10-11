package eu.moncompte.shared.events;


import com.google.web.bindery.event.shared.Event;

/**
 * Created with IntelliJ IDEA.
 * User: cedric
 * Date: 08/07/12
 * Time: 19:20
 * To change this template use File | Settings | File Templates.
 */
public class LogonUserEvent extends Event<LogonUserEventHandler> {
    String email;
    String password;

    public LogonUserEvent(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Type<LogonUserEventHandler> getAssociatedType() {
        return LogonUserEventHandler.TYPE;
    }

    @Override
    protected void dispatch(LogonUserEventHandler handler) {
        handler.onUserLogged(this);
    }
}
