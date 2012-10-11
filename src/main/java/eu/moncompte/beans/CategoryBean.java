package eu.moncompte.beans;

import eu.moncompte.model.Category;
import eu.moncompte.model.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: cedric
 * Date: 07/08/12
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@LocalBean
public class CategoryBean {
    @PersistenceContext
    EntityManager entityManager;

    public List<Category> listCategoriesForUser(User user) {
        List<Category> resultList = entityManager.createQuery("SELECT c FROM Category c WHERE c.user = :user").setParameter("user", user).getResultList();
        if (resultList.size() == 0)
            resultList.add(newCategory(user, "Default"));
        return resultList;
    }

    public Category newCategory(User user, String name) {
        Category category = new Category();
        category.setUser(user);
        category.setName(name);
        entityManager.persist(category);
        return category;
    }
}
