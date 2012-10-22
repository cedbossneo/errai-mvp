package org.jboss.errai.mvp.rebind.ioc;

import org.jboss.errai.codegen.Statement;
import org.jboss.errai.codegen.builder.impl.ObjectBuilder;
import org.jboss.errai.codegen.meta.MetaClass;
import org.jboss.errai.codegen.util.Refs;
import org.jboss.errai.codegen.util.Stmt;
import org.jboss.errai.ioc.client.api.CodeDecorator;
import org.jboss.errai.ioc.rebind.ioc.extension.IOCDecoratorExtension;
import org.jboss.errai.ioc.rebind.ioc.injector.api.InjectableInstance;
import org.jboss.errai.ioc.rebind.ioc.injector.api.InjectionContext;
import org.jboss.errai.mvp.client.annotations.ContentSlot;
import org.jboss.errai.mvp.client.events.RevealContentHandler;

import java.util.Arrays;
import java.util.List;

import static org.jboss.errai.codegen.meta.MetaClassFactory.parameterizedAs;
import static org.jboss.errai.codegen.meta.MetaClassFactory.typeParametersOf;

/**
 * Application Logicielle Visitors-Book
 * <p/>
 * Copyright (c) : Jade-i, 2010-2012, All rights reserved.
 * <p/>
 * IDDN.FR.001.500049.000.S.P.2011.000.20700
 * <p/>
 * Auteur : Laurent Vieille, Nicolas Mallot-Touzet, Bernard Wappler, Cedric Hauber, Joel Kinding-Kinding, Paul Duncan
 * <p/>
 * Dernière modification
 * Utilisateur: cedric
 * Date: 22/10/12
 * Heure: 14:28
 */
@CodeDecorator
public class ContentSlotIOCExtension extends IOCDecoratorExtension<ContentSlot> {
    public ContentSlotIOCExtension(final Class<ContentSlot> decoratesWith) {
        super(decoratesWith);
    }

    @Override
    public List<? extends Statement> generateDecorator(final InjectableInstance<ContentSlot> injectableInstance) {
        final InjectionContext ctx = injectableInstance.getInjectionContext();

        /**
         * Ensure the the container generates a stub to internally expose the field if it's private.
         */
        injectableInstance.ensureMemberExposed();

        /**
         * Figure out the service name;
         */
        final MetaClass revealContentHandler =
                parameterizedAs(RevealContentHandler.class, typeParametersOf(injectableInstance.getEnclosingType()));

        // register a destructor to unregister the service when the bean is destroyed.
        final ObjectBuilder registerMeth
                = ObjectBuilder.newInstanceOf(revealContentHandler);

        final Statement descrCallback = Stmt.create().loadVariable("eventBus").invoke("addHandler",
                Refs.get(injectableInstance.getInjector().getInstanceVarName()), registerMeth);

        return Arrays.asList(descrCallback);
    }
}