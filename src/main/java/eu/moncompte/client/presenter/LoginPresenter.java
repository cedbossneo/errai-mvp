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

import com.google.gwt.user.client.Cookies;
import com.google.web.bindery.event.shared.EventBus;
import eu.moncompte.client.NameTokens;
import eu.moncompte.client.uihandlers.LoginUiHandlers;
import eu.moncompte.shared.Session;
import eu.moncompte.shared.events.LogonUserEvent;
import eu.moncompte.shared.events.LogonUserEventHandler;
import eu.moncompte.shared.events.UserLoggedEvent;
import eu.moncompte.shared.exceptions.SessionNotFoundException;
import eu.moncompte.shared.service.UserService;
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

@Dependent
@NameToken(NameTokens.LOGIN)
@ProxyClass
@LoadAsync
public class LoginPresenter
        extends UIHandlerPresenter<LoginPresenter.MyView> implements LoginUiHandlers, LogonUserEventHandler{
    @Inject
    private Caller<UserService> userService;

    @Inject
    private PlaceManager placeManager;

    @Inject
    EventBus eventBus;

    public void onLogin() {
        login(getView().getLogin(), getView().getPassword());
    }

    private void login(String login, String password) {
        userService.call(new RemoteCallback<Session>() {
                              @Override
                              public void callback(Session response) {
                                  onLoginSuccess(response);
                              }
                          }, new ErrorCallback() {
                              @Override
                              public boolean error(Message message, Throwable throwable) {
                                  getView().loginFailed();
                                  return false;
                              }
                          }
        ).login(login, password);
    }

    private void login(String uuid) {
        userService.call(new RemoteCallback<Session>() {
                              @Override
                              public void callback(Session response) {
                                  onLoginSuccess(response);
                              }
                          }, new ErrorCallback() {
                              @Override
                              public boolean error(Message message, Throwable throwable) {
                                  try {
                                      throw throwable;
                                  } catch (SessionNotFoundException e) {
                                      getView().loginFailed();
                                  } catch (Throwable throwable1) {
                                  }
                                  return false;
                              }
                          }
        ).login(uuid);
    }

    private void onLoginSuccess(Session session) {
        Cookies.setCookie("MCSESSION", session.getUuid());
        getView().loginSuccess(session);
        eventBus.fireEvent(new UserLoggedEvent());
    }

    @ProxyEvent
    public void onUserLogged(LogonUserEvent event) {
        placeManager.revealPlace(new PlaceRequest(NameTokens.getLoginPage()));
        login(event.getEmail(), event.getPassword());
    }

    @Inject
    public LoginPresenter(MyView view) {
        super(view);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        String uuid = Cookies.getCookie("MCSESSION");
        if (uuid != null && !uuid.equals("")) {
            login(uuid);
        }
    }

    /**
     * {@link eu.moncompte.client.presenter.LoginPresenter}'s view.
     */
    public static interface MyView extends View, HasUiHandlers<LoginUiHandlers> {
        void loginFailed();

        void loginSuccess(Session session);

        String getLogin();

        String getPassword();
    }
}