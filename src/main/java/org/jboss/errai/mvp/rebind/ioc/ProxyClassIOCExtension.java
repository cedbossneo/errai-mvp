/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.rebind.ioc;


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
        }
    }
}