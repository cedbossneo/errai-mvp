package eu.moncompte.model;

import org.jboss.errai.common.client.api.annotations.Portable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: cedric
 * Date: 13/07/12
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
@Portable
@Entity
@Table(name = "UserRoles")
public class UserRoles implements Serializable {
    public static enum Roles{
        USER, ADMIN
    }

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
