package eu.moncompte.shared.service;

import eu.moncompte.model.User;
import eu.moncompte.shared.Session;
import eu.moncompte.shared.exceptions.SessionNotFoundException;
import org.jboss.errai.bus.server.annotations.Remote;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 9/30/12
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Remote
public interface UserService {
    public void register(String email, String name, String phoneNumber, String password);
    public Session login(String login, String password);
    public Session login(String loginUUID) throws SessionNotFoundException;
    public List<User> listUsers();
}
