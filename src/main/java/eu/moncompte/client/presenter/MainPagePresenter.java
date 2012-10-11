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

import org.jboss.errai.mvp.client.annotations.NameToken;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.views.View;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@NameToken("")
public class MainPagePresenter extends Presenter<MainPagePresenter.MyView> {

    /**
     * {@link eu.moncompte.client.presenter.MainPagePresenter}'s view.
     */
    public static interface MyView extends View {
        void showLoading(boolean visibile);
    }

    @Inject
    public MainPagePresenter(MyView view) {
        super(view);
    }

}
