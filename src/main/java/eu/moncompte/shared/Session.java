package eu.moncompte.shared;

import eu.moncompte.model.User;
import org.jboss.errai.common.client.api.annotations.Portable;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: cedric
 * Date: 05/07/12
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
@Portable
public class Session implements Serializable {
    private User user;
    private String uuid;
    private boolean valid;


    protected Session(User user, String uuid) {
        this.user = user;
        this.uuid = uuid;
        this.valid = true;
    }

    public Session() {
        valid = false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
