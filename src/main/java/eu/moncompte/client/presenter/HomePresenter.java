/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package eu.moncompte.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.sencha.gxt.widget.core.client.info.Info;
import eu.moncompte.client.MC;
import eu.moncompte.client.NameTokens;
import eu.moncompte.client.uihandlers.HomeUiHandlers;
import eu.moncompte.model.Category;
import eu.moncompte.model.Transaction;
import eu.moncompte.shared.events.UserLoggedEvent;
import eu.moncompte.shared.events.UserLoggedEventHandler;
import eu.moncompte.shared.service.CategoriesService;
import eu.moncompte.shared.service.TransactionsService;
import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;
import org.jboss.errai.ioc.client.api.LoadAsync;
import org.jboss.errai.mvp.client.annotations.NameToken;
import org.jboss.errai.mvp.client.annotations.ProxyClass;
import org.jboss.errai.mvp.client.annotations.ProxyEvent;
import org.jboss.errai.mvp.client.places.PlaceManager;
import org.jboss.errai.mvp.client.places.PlaceRequest;
import org.jboss.errai.mvp.client.presenters.UIHandlerPresenter;
import org.jboss.errai.mvp.client.views.HasUiHandlers;
import org.jboss.errai.mvp.client.views.View;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
@NameToken(NameTokens.HOME)
@ProxyClass
@LoadAsync
public class HomePresenter extends
        UIHandlerPresenter<HomePresenter.MyView> implements HomeUiHandlers, UserLoggedEventHandler {
    @Inject
    private PlaceManager placeManager;

    @Inject
    Caller<TransactionsService> transactionService;

    @Inject
    Caller<CategoriesService> categoriesService;

    @Inject
    EventBus eventBus;

    public void getTransactions(final AsyncCallback<ListLoadResult<Transaction>> callback) {
        transactionService.call(new RemoteCallback<List<Transaction>>() {
                                    @Override
                                    public void callback(List<Transaction> transactions) {
                                        callback.onSuccess(new ListLoadResultBean<Transaction>(transactions));
                                    }
                                }, new ErrorCallback() {
                                    @Override
                                    public boolean error(Message message, Throwable throwable) {
                                        callback.onFailure(throwable);
                                        return false;
                                    }
                                }
        ).listTransactions();
    }

    public void onRemove(Transaction item) {
        transactionService.call(new RemoteCallback<Void>() {
                                    @Override
                                    public void callback(Void o) {
                                        getView().refreshGrid();
                                    }
                                }, new ErrorCallback() {
                                    @Override
                                    public boolean error(Message message, Throwable throwable) {
                                        Info.display("MonCompte.eu", MC.Messages.errorRemove());
                                        return false;
                                    }
                                }

        ).removeTransaction(item);
    }

    public void onUpdate(Transaction change) {
        transactionService.call(new RemoteCallback<Void>() {
                                    @Override
                                    public void callback(Void o) {
                                        getView().refreshGrid();
                                    }
                                }, new ErrorCallback() {
                                    @Override
                                    public boolean error(Message message, Throwable throwable) {
                                        Info.display("MonCompte.eu", MC.Messages.errorUpdate());
                                        return false;
                                    }
                                }

        ).updateTransaction(change);
    }

    public void addTransaction() {
        transactionService.call(new RemoteCallback<Transaction>() {
                                    @Override
                                    public void callback(Transaction o) {
                                        getView().newTransaction(o);
                                    }
                                }, new ErrorCallback() {
                                    @Override
                                    public boolean error(Message message, Throwable throwable) {
                                        Info.display("MonCompte.eu", MC.Messages.errorNew());
                                        return false;
                                    }
                                }

        ).newTransaction();
    }

    public void getCategories(final AsyncCallback<PagingLoadResult<Category>> callback) {
        categoriesService.call(new RemoteCallback<List<Category>>() {
            @Override
            public void callback(List<Category> response) {
                callback.onSuccess(new PagingLoadResultBean<Category>(response, response.size(), 0));
            }
        }).listCategories();
    }


    @ProxyEvent
    public void onUserLogged(UserLoggedEvent event) {
       placeManager.revealPlace(new PlaceRequest(NameTokens.getHomePage()));
    }

    /**
     * {@link eu.moncompte.client.presenter.HomePresenter}'s view.
     */
    public static interface MyView extends View, HasUiHandlers<HomeUiHandlers> {
        void refreshGrid();

        void newTransaction(Transaction newTransaction);
    }

    @Inject
    public HomePresenter(MyView view) {
        super(view);
    }
}