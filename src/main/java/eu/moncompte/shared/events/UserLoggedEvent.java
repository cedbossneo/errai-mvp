package eu.moncompte.shared.events;


import com.google.web.bindery.event.shared.Event;

/**
 * Created with IntelliJ IDEA.
 * User: cedric
 * Date: 03/07/12
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public class UserLoggedEvent extends Event<UserLoggedEventHandler> {
    @Override
    public Type<UserLoggedEventHandler> getAssociatedType() {
        return UserLoggedEventHandler.TYPE;
    }

    @Override
    protected void dispatch(UserLoggedEventHandler handler) {
        handler.onUserLogged(this);
    }
}
