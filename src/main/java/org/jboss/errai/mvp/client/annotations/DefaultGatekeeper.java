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

package org.jboss.errai.mvp.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Use this annotation in your custom ginjector to annotate a
 * {@link org.jboss.errai.mvp.client.places.Gatekeeper}-derived class. This class
 * will be used to provide places for proxies that are not annotated with the
 * {@link UseGatekeeper} annotation.
 *
 * @author Philippe Beaudoin
 */
@Target(ElementType.TYPE)
public @interface DefaultGatekeeper {
}
