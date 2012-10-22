/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.rebind.ioc;


import com.google.gwt.event.shared.GwtEvent;
import org.jboss.errai.codegen.Statement;
import org.jboss.errai.codegen.meta.MetaField;
import org.jboss.errai.codegen.util.Refs;
import org.jboss.errai.mvp.client.annotations.ContentSlot;
import org.jboss.errai.mvp.client.annotations.ProxyClass;
import org.jboss.errai.mvp.client.annotations.ProxyEvent;
import org.jboss.errai.mvp.client.events.LazyEventBus;
import org.jboss.errai.codegen.BlockStatement;
import org.jboss.errai.codegen.meta.MetaClass;
import org.jboss.errai.codegen.meta.MetaMethod;
import org.jboss.errai.codegen.meta.MetaParameter;
import org.jboss.errai.codegen.util.Stmt;
import org.jboss.errai.config.util.ClassScanner;
import org.jboss.errai.ioc.client.api.IOCExtension;
import org.jboss.errai.ioc.rebind.ioc.bootstrapper.IOCProcessingContext;
import org.jboss.errai.ioc.rebind.ioc.bootstrapper.IOCProcessorFactory;
import org.jboss.errai.ioc.rebind.ioc.extension.IOCExtensionConfigurator;
import org.jboss.errai.ioc.rebind.ioc.injector.api.InjectionContext;
import org.jboss.errai.mvp.client.events.RevealContentHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.jboss.errai.codegen.meta.MetaClassFactory.parameterizedAs;
import static org.jboss.errai.codegen.meta.MetaClassFactory.typeParametersOf;

@SuppressWarnings("UnusedDeclaration")
@IOCExtension
public class ProxyClassIOCExtension implements IOCExtensionConfigurator {

    public ProxyClassIOCExtension() {
    }

    @Override
    public void configure(IOCProcessingContext context, InjectionContext injectionContext, IOCProcessorFactory procFactory) {
    }

    @Override
    public void afterInitialization(IOCProcessingContext context, InjectionContext injectionContext, IOCProcessorFactory procFactory) {
        final BlockStatement instanceInitializer = context.getBootstrapClass().getInstanceInitializer();

        for (MetaClass klass : ClassScanner.getTypesAnnotatedWith(ProxyClass.class)){
            for (MetaMethod method : klass.getMethodsAnnotatedWith(ProxyEvent.class)) {
                MetaParameter event = method.getParameters()[0];
                instanceInitializer.addStatement(Stmt.invokeStatic(LazyEventBus.class, "registerProxyEvent", event.getType(), klass));
            }
            for (MetaField field : klass.getFields()) {
                if (!field.isStatic())
                    continue;
                if (!field.isAnnotationPresent(ContentSlot.class))
                    continue;
                MetaClass revealContentHandler =
                        parameterizedAs(RevealContentHandler.class, typeParametersOf(klass));
                try {
                    Object handler = revealContentHandler.asClass().getConstructor(Class.class).newInstance(klass.asClass());
                    instanceInitializer.addStatement(Stmt.invokeStatic(LazyEventBus.class, "registerHandler", Stmt.loadStatic(klass, field.getName()), handler));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InstantiationException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvocationTargetException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }
}