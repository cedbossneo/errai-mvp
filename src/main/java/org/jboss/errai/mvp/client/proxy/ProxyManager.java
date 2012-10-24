package org.jboss.errai.mvp.client.proxy;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Command;
import com.google.web.bindery.event.shared.EventBus;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.mvp.client.events.*;
import org.jboss.errai.mvp.client.places.*;
import org.jboss.errai.mvp.client.presenters.Presenter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Inject
    PlaceManager placeManager;

    private static ProxyManager instance;

    static List<DefferedProxy> defferedProxies = new ArrayList<DefferedProxy>();
    static Map<Class<? extends Presenter<?>>, Proxy> proxies = new HashMap<Class<? extends Presenter<?>>, Proxy>();

    public static <P extends Presenter<?>> void registerPlace(String token, Class<P> presenterClass){
        DefferedProxyPlace<P> proxyPlace = new DefferedProxyPlace<P>(token, presenterClass);
        defferedProxies.add(proxyPlace);
    }

    public static <P extends Presenter<?>> void registerPlace(String token, Class<P> presenterClass, Class<? extends Gatekeeper> gateKeeper){
        DefferedGateKeeperProxyPlace<P> proxyPlace = new DefferedGateKeeperProxyPlace<P>(token, presenterClass, gateKeeper);
        defferedProxies.add(proxyPlace);
    }

    @PostConstruct
    public void init(){
        instance = this;
        for (DefferedProxy defferedProxy : defferedProxies){
            proxies.put(defferedProxy.getPresenterClass(), defferedProxy.makeProxy(eventBus, placeManager));
        }
    }

    public static <P extends Presenter<?>> Proxy<P> getPresenterProxy(Class<P> presenterClass){
        return proxies.get(presenterClass);
    }

    public static <T extends Presenter<?>> void getPresenter(Class<T> persenterClass, NotifyingAsyncCallback<T> notifyingAsyncCallback){
        notifyingAsyncCallback.prepare();
        IOCBeanDef<T> tiocBeanDef = instance.manager.lookupBean(persenterClass);
        if (tiocBeanDef == null)
            notifyingAsyncCallback.onFailure(new Throwable("Bean definition not found"));
        notifyingAsyncCallback.checkLoading();
        T bean = tiocBeanDef.getInstance();
        if (bean == null)
            notifyingAsyncCallback.onFailure(new Throwable("Error while getting bean"));
        else
            notifyingAsyncCallback.onSuccess(bean);
    }
}
