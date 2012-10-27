/*
 * Errai-MVP, inspired by GWT-Platform for the Errai Framework
 * Copyright : Cedric Hauber (cedbossneo) 2012.
 */

package org.jboss.errai.mvp.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Use this annotation in classes implementing {@link com.gwtplatform.mvp.client.Presenter} and that have
 * slots to display child presenters. This annotates every static field
 * containing a type of event that is monitored by this presenter. When handling
 * this event, a child presenter is inserted in the presenter's view. You should
 * make sure the view handles event of this type in its
 * {@link com.gwtplatform.mvp.client.View#setInSlot(Object, com.google.gwt.user.client.ui.Widget)} method.
 *
 * @author Philippe Beaudoin
 */
@Target(ElementType.METHOD)
public @interface ContentSlot {
}
