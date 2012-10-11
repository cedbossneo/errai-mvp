/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.rebind.ioc;


import org.jboss.errai.mvp.client.annotations.ProxyClass;
import org.jboss.errai.mvp.client.events.LazyEventBus;
import org.jboss.errai.codegen.Parameter;
import org.jboss.errai.codegen.Statement;
import org.jboss.errai.codegen.builder.AnonymousClassStructureBuilder;
import org.jboss.errai.codegen.builder.BlockBuilder;
import org.jboss.errai.codegen.builder.impl.ObjectBuilder;
import org.jboss.errai.codegen.meta.MetaClass;
import org.jboss.errai.codegen.util.Refs;
import org.jboss.errai.codegen.util.Stmt;
import org.jboss.errai.ioc.client.api.CodeDecorator;
import org.jboss.errai.ioc.client.container.DestructionCallback;
import org.jboss.errai.ioc.rebind.ioc.extension.IOCDecoratorExtension;
import org.jboss.errai.ioc.rebind.ioc.injector.api.InjectableInstance;
import org.jboss.errai.ioc.rebind.ioc.injector.api.InjectionContext;

import java.util.Arrays;
import java.util.List;

import static org.jboss.errai.codegen.meta.MetaClassFactory.parameterizedAs;
import static org.jboss.errai.codegen.meta.MetaClassFactory.typeParametersOf;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 10/4/12
 * Time: 7:59 PM
 * To change this template use File | Settings | File Templates.
 */
@CodeDecorator
public class ProxyEventIOCExtension extends IOCDecoratorExtension<ProxyClass> {
    public ProxyEventIOCExtension(final Class<ProxyClass> decoratesWith) {
        super(decoratesWith);
    }

    @Override
    public List<? extends Statement> generateDecorator(final InjectableInstance<ProxyClass> injectableInstance) {
        final InjectionContext ctx = injectableInstance.getInjectionContext();

        /**
         * Ensure the the container generates a stub to internally expose the field if it's private.
         */
        injectableInstance.ensureMemberExposed();

        /**
         * Figure out the service name;
         */
        final MetaClass destructionCallbackType =
                parameterizedAs(DestructionCallback.class, typeParametersOf(injectableInstance.getEnclosingType()));

        // register a destructor to unregister the service when the bean is destroyed.
        final BlockBuilder<AnonymousClassStructureBuilder> destroyMeth
                = ObjectBuilder.newInstanceOf(destructionCallbackType).extend()
                .publicOverridesMethod("destroy", Parameter.of(injectableInstance.getEnclosingType(), "obj", true))
                .append(Stmt.invokeStatic(LazyEventBus.class, "unregisterProxyEvent", injectableInstance.getType()));

        final Statement descrCallback = Stmt.create().loadVariable("context").invoke("addDestructionCallback",
                Refs.get(injectableInstance.getInjector().getInstanceVarName()), destroyMeth.finish().finish());

        return Arrays.asList(descrCallback);
    }
}