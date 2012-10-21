package org.jboss.errai.mvp.client.proxy;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
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

    public static <T extends Presenter<?>> T getPresenter(Class<T> persenterClass){
        T bean = instance.manager.lookupBean(persenterClass).getInstance();
        return bean;
    }
}
