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
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import eu.moncompte.client.MC;
import eu.moncompte.client.presenter.LoginPresenter;
import eu.moncompte.client.uihandlers.LoginUiHandlers;
import eu.moncompte.shared.Session;
import org.jboss.errai.mvp.client.views.ViewWithUiHandlers;

import javax.annotation.PostConstruct;

/**
 * @author Christian Goudreau
 */
public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> implements LoginPresenter.MyView {

    public static interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
    }

    private LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);

    @UiField
    PasswordField password;
    @UiField
    TextField login;

    private Widget widget;

    @PostConstruct
    public void createUi() {
        widget = uiBinder.createAndBindUi(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @UiHandler("onLogin")
    public void onLogin(SelectEvent selectionEvent) {
        getUiHandlers().onLogin();
    }

    @Override
    public void loginFailed() {
        Info.display("MonCompte.eu", MC.Messages.errorLogin());
        login.markInvalid(MC.Messages.loginIncorrect());
    }

    @Override
    public void loginSuccess(Session session) {
        Info.display("MonCompte.eu", MC.Messages.onLogin(session.getUser().getName()));
    }

    @Override
    public String getLogin() {
        return login.getValue();
    }

    @Override
    public String getPassword() {
        return password.getValue();
    }

    public void passwordFailed() {
        password.markInvalid(MC.Messages.passwordIncorrect());
    }
}