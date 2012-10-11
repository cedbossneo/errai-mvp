/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.client.events;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.SimpleEventBus;
import org.jboss.errai.ioc.client.container.IOCBeanManager;

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
    private IOCBeanManager manager;

    static final Map<Class<? extends Event>, List<Class>> events = new HashMap<Class<? extends Event>, List<Class>>();
    static final Map<Class<? extends Event>, List<Class>> handled = new HashMap<Class<? extends Event>, List<Class>>();

    public LazyEventBus() {
    }

    public void setManager(IOCBeanManager manager) {
        this.manager = manager;
    }

    @Override
    public void fireEvent(Event<?> event) {
        prepareHandler(event, null);
        super.fireEvent(event);
    }

    private void prepareHandler(Event<?> event, Object source) {
        Class<? extends Event> key = event.getClass();
        if (events.containsKey(key)){
            for (Class klass : events.get(key)){
                Object instance = manager.lookupBean(klass).getInstance();
                if (!handled.containsKey(key) || !handled.get(key).contains(klass)){
                    if (source == null)
                        addHandler((Event.Type<Object>) event.getAssociatedType(), instance);
                    else
                        addHandlerToSource((Event.Type<Object>) event.getAssociatedType(), source, instance);
                    addToMap(handled, key, klass);
                }
            }
        }
    }

    private  static void addToMap(Map<Class<? extends Event>, List<Class>> map, Class<? extends Event> event, Class klass) {
        if (map.containsKey(event)){
            map.get(event).add(klass);
        }else {
            ArrayList<Class> classes = new ArrayList<Class>();
            classes.add(klass);
            map.put(event, classes);
        }
    }

    @Override
    public void fireEventFromSource(Event<?> event, Object source) {
        prepareHandler(event, source);
        super.fireEventFromSource(event, source);
    }

    public static void registerProxyEvent(Class<? extends Event> event, Class klass){
        addToMap(events, event, klass);
    }

    public static void unregisterProxyEvent(Class klass){
        for (List<Class> klasses : handled.values()){
            if (klasses.contains(klass))
                klasses.remove(klass);
        }
    }
}
