package eu.moncompte.model;

import org.jboss.errai.common.client.api.annotations.Portable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: cedric
 * Date: 07/08/12
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
@Portable
@Entity
@Table(name = "Category")
public class Category implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    User user;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
