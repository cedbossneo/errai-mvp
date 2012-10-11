package eu.moncompte.client.uihandlers;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import eu.moncompte.model.Category;
import eu.moncompte.model.Transaction;
import org.jboss.errai.mvp.client.views.UiHandlers;

/**
 * Created by IntelliJ IDEA.
 * User: hauber_c
 * Date: 12/07/12
 * Time: 11:52
 */
public interface HomeUiHandlers extends UiHandlers {
    public void getTransactions(AsyncCallback<ListLoadResult<Transaction>> callback);

    void onRemove(Transaction item);

    void onUpdate(Transaction change);

    void addTransaction();

    void getCategories(AsyncCallback<PagingLoadResult<Category>> callback);
}
