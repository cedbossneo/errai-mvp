package eu.moncompte.server.session;

import eu.moncompte.model.User;
import eu.moncompte.shared.Session;
import org.jboss.errai.bus.server.api.RpcContext;

import javax.inject.Singleton;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: cedric
 * Date: 05/07/12
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class SessionManager {
    private Map<String, Session> sessions;

    private SessionManager() {
        sessions = new ConcurrentHashMap<String, Session>();
    }

    public Session newSession(User user){
        Session newSession = new Session();
        String uuid = UUID.randomUUID().toString();
        while (sessions.containsKey(uuid))
            uuid = UUID.randomUUID().toString();
        newSession.setUuid(uuid);
        sessions.put(newSession.getUuid(), newSession);
        newSession.setUser(user);
        newSession.setValid(true);
        return newSession;
    }

    public Session getSession(){
        HttpSession session = RpcContext.getHttpSession();
        String uuid = (String) session.getAttribute("uuid");
        if (uuid == null)
            return null;
        return getSession(uuid);
    }

    public Session getSession(String uuid){
        return sessions.get(uuid);
    }

    public List<Session> getSession(User user){
        List<Session> res = new ArrayList<Session>();
        for (String uuid : sessions.keySet()){
            Session session = sessions.get(uuid);
            if (session.getUser().equals(user)){
                res.add(session);
            }
        }
        return res;
    }

    public void removeSession(String uuid){
        sessions.remove(uuid);
    }

    public void removeSession(Session session){
        removeSession(session.getUuid());
    }

    public void removeSession(User user){
        List<Session> sessionList = getSession(user);
        for (Session session : sessionList)
            removeSession(session.getUuid());
    }

}
