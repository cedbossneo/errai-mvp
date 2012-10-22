package org.jboss.errai.mvp.client.proxy;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.mvp.client.events.NotifyingAsyncCallback;
import org.jboss.errai.mvp.client.presenters.Presenter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 10/21/12
 * Time: 11:55 PM
 * To change this template use File | Settings | File Templates.
 */
@ApplicationScoped
public class ProxyManager {
    @Inject
    private IOCBeanManager manager;

    @Inject
    EventBus eventBus;

    private static ProxyManager instance;

    @PostConstruct
    public void init(){
        instance = this;
    }

    public static <T extends Presenter<?>> void getPresenter(Class<T> persenterClass, NotifyingAsyncCallback<T> notifyingAsyncCallback){
        T bean = instance.manager.lookupBean(persenterClass).getInstance();
        if (bean == null)
            notifyingAsyncCallback.onFailure(new Throwable());
        else
            notifyingAsyncCallback.onSuccess(bean);
    }
}
