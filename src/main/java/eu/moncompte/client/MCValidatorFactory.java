package eu.moncompte.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;
import eu.moncompte.model.User;

import javax.validation.Validator;
import javax.validation.groups.Default;

/**
 * GWT's Bean Validation implementation needs to be told up front which classes
 * can be validated. In a future version of Errai, we will discover beans with
 * validation annotations, making a factory like this one optional.
 *
 * @author Jonathan Fuerth <jfuerth@redhat.com>
 * @author Christian Sadilek <csadilek@redhat.com>
 */
public final class MCValidatorFactory extends AbstractGwtValidatorFactory {

  @GwtValidation(value = User.class, groups = {Default.class})
  public interface GwtValidator extends Validator {
  }

  @Override
  public AbstractGwtValidator createValidator() {
    return GWT.create(GwtValidator.class);
  }
}