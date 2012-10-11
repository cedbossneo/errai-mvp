package eu.moncompte.server.services;

import eu.moncompte.beans.UserBean;
import eu.moncompte.model.User;
import eu.moncompte.model.UserRoles;
import eu.moncompte.server.session.SessionManager;
import eu.moncompte.shared.Session;
import eu.moncompte.shared.exceptions.SessionNotFoundException;
import eu.moncompte.shared.service.UserService;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.bus.server.api.RpcContext;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 9/29/12
 * Time: 4:47 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserServiceImpl implements UserService {
    @EJB
    UserBean userBean;

    @Inject
    SessionManager sessionManager;

    @Override
    public void register(String email, String name, String phoneNumber, String password) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setPhoneNumber(phoneNumber);
        newUser = userBean.register(newUser);
        userBean.addRole(newUser, UserRoles.Roles.USER);
    }

    @Override
    public Session login(String login, String password) {
        User user = userBean.login(login, password);
        Session session = sessionManager.newSession(user);
        HttpSession httpSession = RpcContext.getHttpSession();
        httpSession.setAttribute("uuid", session.getUuid());
        return session;
    }

    @Override
    public Session login(String loginUUID) throws SessionNotFoundException {
        Session session = sessionManager.getSession(loginUUID);
        if (session != null){
            return session;
        }else
            throw new SessionNotFoundException();
    }

    @Override
    public List<User> listUsers() {
        return userBean.getUsers();
    }
}
