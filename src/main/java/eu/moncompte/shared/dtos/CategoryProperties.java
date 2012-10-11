package eu.moncompte.shared.dtos;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import eu.moncompte.model.Category;

/**
 * Created with IntelliJ IDEA.
 * User: cedric
 * Date: 07/08/12
 * Time: 23:54
 * To change this template use File | Settings | File Templates.
 */
public interface CategoryProperties extends PropertyAccess<Category> {
    ModelKeyProvider<Category> id();
    LabelProvider<Category> name();
}
