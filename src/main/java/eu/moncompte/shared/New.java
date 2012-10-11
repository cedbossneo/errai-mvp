package eu.moncompte.shared;

import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Like the built-in CDI {@code @New} annotation, but <i>newer</i>.
 *
 * @author Jonathan Fuerth <jfuerth@redhat.com>
 * @author Christian Sadilek <csadilek@redhat.com>
 */
@Target( { FIELD, PARAMETER, METHOD, TYPE })
@Retention(RUNTIME)
@Documented
@Qualifier
public @interface New {

}
