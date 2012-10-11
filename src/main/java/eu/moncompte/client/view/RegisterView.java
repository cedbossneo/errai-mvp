package eu.moncompte.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.RegExValidator;
import eu.moncompte.client.presenter.RegisterPresenter;
import eu.moncompte.client.uihandlers.RegisterUiHandlers;
import org.jboss.errai.mvp.client.views.ViewWithUiHandlers;

import javax.annotation.PostConstruct;

/**
 * Created with IntelliJ IDEA.
 * User: cedric
 * Date: 08/07/12
 * Time: 17:55
 * To change this template use File | Settings | File Templates.
 */
public class RegisterView extends ViewWithUiHandlers<RegisterUiHandlers> implements RegisterPresenter.MyView {
    public static interface RegisterViewUiBinder extends UiBinder<Widget, RegisterView> {
    }

    private RegisterViewUiBinder uiBinder = GWT.create(RegisterViewUiBinder.class);

    private Widget widget;

    @UiField
    TextField name;
    @UiField
    TextField email;
    @UiField
    PasswordField password;
    @UiField
    TextField phoneNumber;

    public RegisterView() {
    }

    @PostConstruct
    public void createUi() {
        widget = uiBinder.createAndBindUi(this);
        email.addValidator(new RegExValidator("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
                + "(" + "[a-z0-9!#$%&'*+/=?^_`{|}~-]" + "+(\\." + "[a-z0-9!#$%&'*+/=?^_`{|}~-]" + "+)*"
                + "|"
                + "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]"
                + ")$"));
        phoneNumber.addValidator(new RegExValidator("^[0-9]{10,12}$"));
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @UiHandler("onRegister")
    public void onRegister(SelectEvent selectionEvent) {
            getUiHandlers().onRegister();
    }

    @Override
    public String getEmail() {
        return email.getValue();
    }

    @Override
    public String getName() {
        return name.getValue();
    }

    @Override
    public String getPhone() {
        return phoneNumber.getValue();
    }

    @Override
    public String getPassword() {
        return password.getValue();
    }
}
