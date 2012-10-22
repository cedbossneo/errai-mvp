/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.rebind.ioc;

import org.jboss.errai.codegen.BlockStatement;
import org.jboss.errai.codegen.meta.MetaClass;
import org.jboss.errai.codegen.util.Stmt;
import org.jboss.errai.config.util.ClassScanner;
import org.jboss.errai.ioc.client.api.IOCExtension;
import org.jboss.errai.ioc.rebind.ioc.bootstrapper.IOCProcessingContext;
import org.jboss.errai.ioc.rebind.ioc.bootstrapper.IOCProcessorFactory;
import org.jboss.errai.ioc.rebind.ioc.extension.IOCExtensionConfigurator;
import org.jboss.errai.ioc.rebind.ioc.injector.api.InjectionContext;
import org.jboss.errai.mvp.client.annotations.NameToken;
import org.jboss.errai.mvp.client.proxy.ProxyManager;

@SuppressWarnings("UnusedDeclaration")
@IOCExtension
public class NameTokenIOCExtension implements IOCExtensionConfigurator {

    public NameTokenIOCExtension() {
    }

    @Override
    public void configure(IOCProcessingContext context, InjectionContext injectionContext, IOCProcessorFactory procFactory) {
    }

    @Override
    public void afterInitialization(IOCProcessingContext context, InjectionContext injectionContext, IOCProcessorFactory procFactory) {
        final BlockStatement instanceInitializer = context.getBootstrapClass().getInstanceInitializer();

        for (MetaClass klass : ClassScanner.getTypesAnnotatedWith(NameToken.class)) {
            instanceInitializer.addStatement(Stmt.invokeStatic(ProxyManager.class, "registerPlace", klass.getAnnotation(NameToken.class).value(), klass));
        }
    }
}