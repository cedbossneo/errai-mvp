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

package org.jboss.errai.mvp.client.events;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.Command;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.proxy.ProxyManager;

/**
 * This is the handler class for {@link RevealContentEvent}. It should be used
 * by any {@link Proxy} class of a {@link Presenter} that accepts child
 * presenters. When this handler is triggered, the proxy should <b>first</b> set
 * the content appropriately in the presenter, and then reveal the presenter.
 *
 * @param <T> The Presenter's type.
 *
 * @author Philippe Beaudoin
 */
public class RevealContentHandler<T extends Presenter<?>> implements EventHandler {

    private Class<T> presenterClass;

    public RevealContentHandler(Class<T> presenterClass) {
        this.presenterClass = presenterClass;
    }

  /**
   * This is the dispatched method. Reveals
   *
   * @param revealContentEvent The event containing the presenter that wants to
   *          bet set as content.
   */
  public final void onRevealContent(final RevealContentEvent revealContentEvent) {
      ProxyManager.getPresenter(presenterClass, new NotifyingAsyncCallback<T>(){

          @Override
          protected void success(final T presenter) {
              Scheduler.get().scheduleDeferred(new Command() {
                  @Override
                  public void execute() {
                      presenter.forceReveal();
                      presenter.setInSlot(revealContentEvent.getAssociatedType(),
                              revealContentEvent.getContent());
                  }
              });
          }
      });
  }

}
