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

package eu.moncompte.client.view;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.event.StoreRemoveEvent;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;
import com.sencha.gxt.data.shared.loader.*;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.grid.*;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;
import eu.moncompte.client.presenter.HomePresenter.MyView;
import eu.moncompte.client.renderers.AggregationCurrencySummaryRenderer;
import eu.moncompte.client.renderers.CurrencyPropertyDisplayCell;
import eu.moncompte.client.uihandlers.HomeUiHandlers;
import eu.moncompte.model.Category;
import eu.moncompte.model.Transaction;
import eu.moncompte.shared.dtos.CategoryProperties;
import eu.moncompte.shared.dtos.TransactionProperties;
import org.jboss.errai.mvp.client.views.ViewWithUiHandlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Christian Goudreau
 */
public class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements MyView {

    public static interface HomeViewUiBinder extends UiBinder<Widget, HomeView> {
    }

    private HomeViewUiBinder uiBinder = GWT.create(HomeViewUiBinder.class);

    @UiField(provided = true)
    ColumnModel<Transaction> cm;

    @UiField(provided = true)
    ListStore<Transaction> store;

    @UiField
    Grid<Transaction> grid;

    @UiField
    GridView<Transaction> view;

    @UiField
    FramedPanel panel;

    @UiField
    VerticalLayoutContainer.VerticalLayoutData topData;

    @UiField
    VerticalLayoutContainer.VerticalLayoutData middleData;

    @UiFactory
    ColumnModel<Transaction> createColumnModel() {
        return cm;
    }

    @UiFactory
    ListStore<Transaction> createListStore() {
        return store;
    }

    public HomeView() {
    }

    @Override
    public Widget asWidget() {
        RpcProxy<ListLoadConfig, ListLoadResult<Transaction>> proxy = new RpcProxy<ListLoadConfig, ListLoadResult<Transaction>>() {
            @Override
            public void load(ListLoadConfig loadConfig, AsyncCallback<ListLoadResult<Transaction>> callback) {
                getUiHandlers().getTransactions(callback);
            }
        };

        TransactionProperties props = GWT.create(TransactionProperties.class);

        store = new ListStore<Transaction>(props.id());
        store.addStoreUpdateHandler(new StoreUpdateEvent.StoreUpdateHandler<Transaction>() {
            @Override
            public void onUpdate(StoreUpdateEvent<Transaction> event) {
                for (Transaction change : event.getItems()) {
                    getUiHandlers().onUpdate(change);
                }
            }
        });
        store.addStoreRemoveHandler(new StoreRemoveEvent.StoreRemoveHandler<Transaction>() {
            @Override
            public void onRemove(StoreRemoveEvent<Transaction> event) {
                getUiHandlers().onRemove(event.getItem());
            }
        });

        final ListLoader<ListLoadConfig, ListLoadResult<Transaction>> loader = new ListLoader<ListLoadConfig, ListLoadResult<Transaction>>(
                proxy);
        loader.addLoadHandler(new LoadResultListStoreBinding<ListLoadConfig, Transaction, ListLoadResult<Transaction>>(store));

        ColumnConfig<Transaction, String> nameColumn = new ColumnConfig<Transaction, String>(props.name(), 150, "Nom");
        ColumnConfig<Transaction, Category> categoryColumn = new ColumnConfig<Transaction, Category>(props.category(), 150, "Catégorie");
        ColumnConfig<Transaction, Date> dateColumn = new ColumnConfig<Transaction, Date>(props.date(), 150, "Date");
        ColumnConfig<Transaction, Float> priceColumn = new ColumnConfig<Transaction, Float>(props.price(), 150, "Prix");
        categoryColumn.setCell(new SimpleSafeHtmlCell<Category>(new AbstractSafeHtmlRenderer<Category>() {

            @Override
            public SafeHtml render(Category object) {
                return SafeHtmlUtils.fromString(object.getName());
            }
        }));
        dateColumn.setCell(new DateCell(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT)));
        NumberFormat currencyFormat = NumberFormat.getCurrencyFormat("EUR");
        priceColumn.setCell(new CurrencyPropertyDisplayCell(currencyFormat));

        List<ColumnConfig<Transaction, ?>> l = new ArrayList<ColumnConfig<Transaction, ?>>();
        l.add(nameColumn);
        l.add(categoryColumn);
        l.add(dateColumn);
        l.add(priceColumn);

        cm = new ColumnModel<Transaction>(l);

        AggregationRowConfig<Transaction> averages = new AggregationRowConfig<Transaction>();
        averages.setRenderer(nameColumn, new AggregationSafeHtmlRenderer<Transaction>("Total"));
        averages.setRenderer(priceColumn, new AggregationCurrencySummaryRenderer(currencyFormat));
        cm.addAggregationRow(averages);

        Widget widget = uiBinder.createAndBindUi(this);

        grid.setLoader(loader);

        GridRowEditing<Transaction> editing = new GridRowEditing<Transaction>(grid);
        editing.addEditor(nameColumn, new TextField());
        editing.addEditor(categoryColumn, getCategoryEditor());
        editing.addEditor(dateColumn, new DateField());
        editing.addEditor(priceColumn, new NumberField<Float>(new NumberPropertyEditor.FloatPropertyEditor()));
        return widget;
    }

    private ComboBox<Category> getCategoryEditor() {
        CategoryProperties categoryProps = GWT.create(CategoryProperties.class);

        ListStore<Category> categoryStore = new ListStore<Category>(categoryProps.id());
        RpcProxy<PagingLoadConfig, PagingLoadResult<Category>> categoryProxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<Category>>() {
            @Override
            public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<Category>> callback) {
                getUiHandlers().getCategories(callback);
            }
        };
        final PagingLoader<PagingLoadConfig, PagingLoadResult<Category>> categoryLoader = new PagingLoader<PagingLoadConfig, PagingLoadResult<Category>>(
                categoryProxy);
        categoryLoader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, Category, PagingLoadResult<Category>>(categoryStore));
        ComboBox<Category> comboBox = new ComboBox<Category>(categoryStore, categoryProps.name());
        comboBox.setLoader(categoryLoader);
        return comboBox;
    }

    @UiHandler("onNew")
    public void onNew(SelectEvent selectionEvent) {
        getUiHandlers().addTransaction();
    }

    @UiHandler("onDelete")
    public void onDelete(SelectEvent selectionEvent) {
        List<Transaction> selectedItems = grid.getSelectionModel().getSelectedItems();
        for (Transaction Transaction : selectedItems)
            store.remove(Transaction);
    }

    @UiHandler("onSave")
    public void onSave(SelectEvent selectionEvent) {
        store.commitChanges();
    }

    @UiHandler("onReset")
    public void onReset(SelectEvent selectionEvent) {
        store.rejectChanges();
    }

    @Override
    public void refreshGrid() {
        grid.getLoader().load();
    }

    @Override
    public void newTransaction(Transaction newTransaction) {
        store.add(newTransaction);
    }


}