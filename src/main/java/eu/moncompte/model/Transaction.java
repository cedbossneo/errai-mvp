package eu.moncompte.model;

import org.jboss.errai.common.client.api.annotations.Portable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: hauber_c
 * Date: 12/07/12
 * Time: 12:03
 */
@Portable
@Entity
@Table(name = "Transaction")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Category category;

    private String name;
    private Float price;
    private Date date;

    @ManyToOne
    User user;

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
