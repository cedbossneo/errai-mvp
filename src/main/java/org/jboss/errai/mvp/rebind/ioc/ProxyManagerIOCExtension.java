/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.rebind.ioc;


import org.jboss.errai.codegen.BlockStatement;
import org.jboss.errai.codegen.builder.StatementEnd;
import org.jboss.errai.codegen.builder.impl.ObjectBuilder;
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
import org.jboss.errai.mvp.client.annotations.*;
import org.jboss.errai.mvp.client.events.LazyEventBus;
import org.jboss.errai.mvp.client.events.RevealContentHandler;
import org.jboss.errai.mvp.client.places.Gatekeeper;
import org.jboss.errai.mvp.client.proxy.ProxyManager;

import java.util.Collection;

import static org.jboss.errai.codegen.meta.MetaClassFactory.parameterizedAs;
import static org.jboss.errai.codegen.meta.MetaClassFactory.typeParametersOf;

@SuppressWarnings("UnusedDeclaration")
@IOCExtension
public class ProxyManagerIOCExtension implements IOCExtensionConfigurator {

    public ProxyManagerIOCExtension() {
    }

    @Override
    public void configure(IOCProcessingContext context, InjectionContext injectionContext, IOCProcessorFactory procFactory) {
    }

    @Override
    public void afterInitialization(IOCProcessingContext context, InjectionContext injectionContext, IOCProcessorFactory procFactory) {
        final BlockStatement instanceInitializer = context.getBootstrapClass().getInstanceInitializer();

        for (MetaClass klass : ClassScanner.getTypesAnnotatedWith(ProxyClass.class)){
            instanceInitializer.addStatement(Stmt.invokeStatic(ProxyManager.class, "registerProxy", klass));
            for (MetaMethod method : klass.getMethodsAnnotatedWith(ProxyEvent.class)) {
                MetaParameter event = method.getParameters()[0];
                instanceInitializer.addStatement(Stmt.invokeStatic(ProxyManager.class, "registerEvent", event.getType(), klass));
            }
            for (MetaMethod method : klass.getMethodsAnnotatedWith(ContentSlot.class)) {
                if (!method.isStatic())
                    continue;
                MetaClass revealContentHandler =
                        parameterizedAs(RevealContentHandler.class, typeParametersOf(klass));
                StatementEnd handler = ObjectBuilder.newInstanceOf(revealContentHandler).withParameters(klass.asClass());
                instanceInitializer.addStatement(Stmt.invokeStatic(LazyEventBus.class, "registerHandler", Stmt.invokeStatic(klass, method.getName()), handler));
            }
        }

        Class<? extends Gatekeeper> defaultGateKeeper = null;
        Collection<MetaClass> defaultGatekeeperClasses = ClassScanner.getTypesAnnotatedWith(DefaultGatekeeper.class);
        if (defaultGatekeeperClasses.size() > 0){
            Class<? extends Gatekeeper> aClass = (Class<? extends Gatekeeper>) defaultGatekeeperClasses.iterator().next().asClass();
            defaultGateKeeper = aClass;
        }

        for (MetaClass klass : ClassScanner.getTypesAnnotatedWith(NameToken.class)) {
            boolean useGateKeeper = klass.isAnnotationPresent(UseGatekeeper.class);
            if (useGateKeeper || (defaultGateKeeper != null && !klass.isAnnotationPresent(NoGatekeeper.class))){
                Class<? extends Gatekeeper> gateKeeper = defaultGateKeeper;
                if (useGateKeeper){
                    Class<? extends Gatekeeper> value = klass.getAnnotation(UseGatekeeper.class).value();
                    gateKeeper = (value != null) ? value : gateKeeper;
                }
                instanceInitializer.addStatement(Stmt.invokeStatic(ProxyManager.class, "registerPlace", klass.getAnnotation(NameToken.class).value(), klass, gateKeeper));
            }else
                instanceInitializer.addStatement(Stmt.invokeStatic(ProxyManager.class, "registerPlace", klass.getAnnotation(NameToken.class).value(), klass));
        }
    }
}