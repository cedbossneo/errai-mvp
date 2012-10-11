package eu.moncompte.server.services;

import eu.moncompte.beans.CategoryBean;
import eu.moncompte.model.Category;
import eu.moncompte.server.session.SessionManager;
import eu.moncompte.shared.service.CategoriesService;
import org.jboss.errai.bus.server.annotations.Service;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 9/29/12
 * Time: 4:58 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CategoriesServiceImpl implements CategoriesService {
    @EJB
    CategoryBean categoryBean;

    @Inject
    SessionManager sessionManager;

    @Override
    public List<Category> listCategories() {
        return categoryBean.listCategoriesForUser(sessionManager.getSession().getUser());
    }
}
