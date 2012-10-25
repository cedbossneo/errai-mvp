/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.client.events;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.SimpleEventBus;
import org.jboss.errai.mvp.client.presenters.Presenter;
import org.jboss.errai.mvp.client.proxy.Proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 10/3/12
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class LazyEventBus extends SimpleEventBus{
    static final Map<Class<? extends Event>, List<Proxy>> events = new HashMap<Class<? extends Event>, List<Proxy>>();
    static final Map<Class<? extends Event>, List<Proxy>> handled = new HashMap<Class<? extends Event>, List<Proxy>>();

    public LazyEventBus() {
        super();
    }

    @Override
    public void fireEvent(Event<?> event) {
        prepareHandler(event, null);
        super.fireEvent(event);
    }

    private void prepareHandler(final Event<?> event, final Object source) {
        final Class<? extends Event> key = event.getClass();
        if (events.containsKey(key)){
            for (final Proxy proxy : events.get(key)){
                proxy.getPresenter(new NotifyingAsyncCallback<Presenter>(this) {
                    @Override
                    protected void success(Presenter result) {
                        if (!handled.containsKey(key) || !handled.get(key).contains(proxy)){
                            if (source == null)
                                addHandler((Event.Type<Object>) event.getAssociatedType(), result);
                            else
                                addHandlerToSource((Event.Type<Object>) event.getAssociatedType(), source, result);
                            addToMap(handled, key, proxy);
                        }
                    }
                });
            }
        }
    }

    private  static void addToMap(Map<Class<? extends Event>, List<Proxy>> map, Class<? extends Event> event, Proxy proxy) {
        if (map.containsKey(event)){
            map.get(event).add(proxy);
        }else {
            ArrayList<Proxy> classes = new ArrayList<Proxy>();
            classes.add(proxy);
            map.put(event, classes);
        }
    }

    @Override
    public void fireEventFromSource(Event<?> event, Object source) {
        prepareHandler(event, source);
        super.fireEventFromSource(event, source);
    }

    public static void registerProxyEvent(Class<? extends Event> event, Proxy proxy){
        addToMap(events, event, proxy);
    }

    public static void unregisterProxyEvent(Proxy proxy){
        for (List<Proxy> klasses : handled.values()){
            if (klasses.contains(proxy))
                klasses.remove(proxy);
        }
    }
}
