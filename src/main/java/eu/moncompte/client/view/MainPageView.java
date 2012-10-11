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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.container.Viewport;
import eu.moncompte.client.presenter.MainPagePresenter.MyView;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * This is the top-level view of the application. Every time another presenter
 * wants to reveal itself, {@link eu.moncompte.client.view.MainPageView} will add its content of the
 * target inside the {@code mainContantPanel}.
 *
 * @author Christian Goudreau
 */
public class MainPageView extends Composite implements MyView {

    public static interface MainPageViewUiBinder extends UiBinder<Widget, MainPageView> {
    }

    private MainPageViewUiBinder uiBinder = GWT.create(MainPageViewUiBinder.class);
    public Widget widget;

    @UiField
    Viewport mainContentPanel;

    @UiField
    Element loadingMessage;

    @Inject
    public MainPageView() {
    }

    @PostConstruct
    public void createUi() {
        widget = uiBinder.createAndBindUi(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    private void setMainContent(Widget content) {
        mainContentPanel.clear();

        if (content != null) {
            mainContentPanel.add(content);
            mainContentPanel.forceLayout();
        }
    }

    @Override
    public void showLoading(boolean visibile) {
        loadingMessage.getStyle().setVisibility(
                visibile ? Visibility.VISIBLE : Visibility.HIDDEN);
    }
}