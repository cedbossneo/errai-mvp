package eu.moncompte.shared.service;

import eu.moncompte.model.Category;
import org.jboss.errai.bus.server.annotations.Remote;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 9/29/12
 * Time: 4:32 AM
 * To change this template use File | Settings | File Templates.
 */
@Remote
public interface CategoriesService {
    public List<Category> listCategories();
}
