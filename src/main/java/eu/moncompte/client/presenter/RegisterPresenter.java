package eu.moncompte.client.presenter;

import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.info.Info;
import eu.moncompte.client.MC;
import eu.moncompte.client.NameTokens;
import eu.moncompte.client.uihandlers.RegisterUiHandlers;
import eu.moncompte.shared.events.LogonUserEvent;
import eu.moncompte.shared.service.UserService;
import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.ioc.client.api.Caller;
import org.jboss.errai.ioc.client.api.LoadAsync;
import org.jboss.errai.mvp.client.annotations.NameToken;
import org.jboss.errai.mvp.client.presenters.UIHandlerPresenter;
import org.jboss.errai.mvp.client.views.HasUiHandlers;
import org.jboss.errai.mvp.client.views.View;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
@NameToken(NameTokens.REGISTER)
@LoadAsync
public class RegisterPresenter extends UIHandlerPresenter<RegisterPresenter.MyView> implements RegisterUiHandlers{
    @Inject
    Caller<UserService> userService;

    @Inject
    EventBus eventBus;

    @Inject
    public RegisterPresenter(MyView view) {
        super(view);
    }

    public void onRegister() {
        userService.call(new RemoteCallback<Void>() {
                                 @Override
                                 public void callback(Void response) {
                                     eventBus.fireEvent(new LogonUserEvent(getView().getEmail(), getView().getPassword()));
                                 }
                             }, new ErrorCallback() {
                                 @Override
                                 public boolean error(Message message, Throwable throwable) {
                                     Info.display("MonCompte.eu", MC.Messages.errorRegister());
                                     return false;  //To change body of implemented methods use File | Settings | File Templates.
                                 }
                             }
        ).register(getView().getEmail(), getView().getName(), getView().getPhone(), getView().getPassword());
    }

    public static interface MyView extends View, HasUiHandlers<RegisterUiHandlers> {
        String getEmail();

        String getName();

        String getPhone();

        String getPassword();
    }
}
