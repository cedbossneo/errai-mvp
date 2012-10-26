/*
 * Copyright (c) 2012.
 * Cedric Hauber
 */

package org.jboss.errai.mvp.rebind.ioc;


import org.jboss.errai.codegen.BlockStatement;
import org.jboss.errai.codegen.Parameter;
import org.jboss.errai.codegen.builder.AnonymousClassStructureBuilder;
import org.jboss.errai.codegen.builder.impl.ObjectBuilder;
import org.jboss.errai.codegen.meta.MetaClass;
import org.jboss.errai.codegen.meta.MetaMethod;
import org.jboss.errai.codegen.meta.MetaParameter;
import org.jboss.errai.codegen.util.Refs;
import org.jboss.errai.codegen.util.Stmt;
import org.jboss.errai.config.util.ClassScanner;
import org.jboss.errai.ioc.client.api.IOCExtension;
import org.jboss.errai.ioc.rebind.ioc.bootstrapper.IOCProcessingContext;
import org.jboss.errai.ioc.rebind.ioc.bootstrapper.IOCProcessorFactory;
import org.jboss.errai.ioc.rebind.ioc.extension.IOCExtensionConfigurator;
import org.jboss.errai.ioc.rebind.ioc.injector.InjectUtil;
import org.jboss.errai.ioc.rebind.ioc.injector.api.InjectionContext;
import org.jboss.errai.mvp.client.annotations.*;
import org.jboss.errai.mvp.client.events.NotifyingAsyncCallback;
import org.jboss.errai.mvp.client.places.Gatekeeper;
import org.jboss.errai.mvp.client.proxy.ProxyImpl;
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

        for (MetaClass klass : ClassScanner.getTypesAnnotatedWith(ProxyClass.class)) {
            AnonymousClassStructureBuilder proxy = createProxy(klass);
            for (MetaMethod method : klass.getMethodsAnnotatedWith(ProxyEvent.class)) {
                MetaParameter event = method.getParameters()[0];
                proxy = createMethod(injectionContext, getHandler(klass, method.getName(), event.getType()), klass, proxy, method.getReturnType(), method.getName(), event);
                MetaMethod staticMethod = event.getType().getBestMatchingStaticMethod("getType", new Class[]{});
                if (staticMethod == null)
                    instanceInitializer.addStatement(Stmt.invokeStatic(ProxyManager.class, "registerEvent", InjectUtil.invokePublicOrPrivateMethod(injectionContext, Stmt.newObject(event.getType()), event.getType().getBestMatchingMethod("getAssociatedType", new Class[]{})), klass));
                else
                    instanceInitializer.addStatement(Stmt.invokeStatic(ProxyManager.class, "registerEvent", Stmt.invokeStatic(event.getType(), staticMethod.getName()), klass));
            }
            instanceInitializer.addStatement(Stmt.invokeStatic(ProxyManager.class, "registerProxy", proxy.finish(), klass));
            for (MetaMethod method : klass.getMethodsAnnotatedWith(ContentSlot.class)) {
                if (!method.isStatic())
                    continue;
                instanceInitializer.addStatement(Stmt.invokeStatic(ProxyManager.class, "registerHandler", Stmt.invokeStatic(klass, method.getName()), klass));
            }
        }

        Class<? extends Gatekeeper> defaultGateKeeper = null;
        Collection<MetaClass> defaultGatekeeperClasses = ClassScanner.getTypesAnnotatedWith(DefaultGatekeeper.class);
        if (defaultGatekeeperClasses.size() > 0) {
            Class<? extends Gatekeeper> aClass = (Class<? extends Gatekeeper>) defaultGatekeeperClasses.iterator().next().asClass();
            defaultGateKeeper = aClass;
        }

        for (MetaClass klass : ClassScanner.getTypesAnnotatedWith(NameToken.class)) {
            boolean useGateKeeper = klass.isAnnotationPresent(UseGatekeeper.class);
            if (useGateKeeper || (defaultGateKeeper != null && !klass.isAnnotationPresent(NoGatekeeper.class))) {
                Class<? extends Gatekeeper> gateKeeper = defaultGateKeeper;
                if (useGateKeeper) {
                    Class<? extends Gatekeeper> value = klass.getAnnotation(UseGatekeeper.class).value();
                    gateKeeper = (value != null) ? value : gateKeeper;
                }
                instanceInitializer.addStatement(Stmt.invokeStatic(ProxyManager.class, "registerPlace", klass.getAnnotation(NameToken.class).value(), klass, gateKeeper));
            } else
                instanceInitializer.addStatement(Stmt.invokeStatic(ProxyManager.class, "registerPlace", klass.getAnnotation(NameToken.class).value(), klass));
        }
    }

    private MetaClass getHandler(MetaClass klass, String name, MetaClass parameter) {
        for (MetaClass handler : klass.getInterfaces()) {
            if (handler.getMethod(name, parameter) != null)
                return handler;
        }
        return null;
    }

    /*        new NotifyingAsyncCallback(){

                        @Override
                        protected void success(final Presenter presenter) {
                            Scheduler.get().scheduleDeferred( new Command() {

                                @Override
                                public void execute() {
                                    presenter.On...(event);
                                }
                            });
                        }
                    })*/
    private AnonymousClassStructureBuilder createMethod(InjectionContext injectionContext, MetaClass handler, MetaClass klass, AnonymousClassStructureBuilder proxy, MetaClass returnType, String name, MetaParameter event) {
        Parameter parameter = Parameter.of(event.getType(), "event", true);
        MetaClass metaClass = parameterizedAs(NotifyingAsyncCallback.class, typeParametersOf(klass));
        if (!proxy.getClassDefinition().isAssignableTo(handler))
            proxy.getClassDefinition().addInterface(handler);
        return proxy.publicMethod(returnType, name, parameter).body()
                .append(
                        InjectUtil.invokePublicOrPrivateMethod(injectionContext, Stmt.loadVariable("this"),
                                proxy.getClassDefinition().getBestMatchingMethod("getPresenter", metaClass),
                                createCallback(metaClass, klass, proxy, name, parameter))
                ).finish();
    }

    private ObjectBuilder createCallback(MetaClass callbackClass, MetaClass presenterKlass, AnonymousClassStructureBuilder proxy, String name, Parameter parameter) {
        Parameter presenter = Parameter.of(presenterKlass, "presenter", true);
        return Stmt.newObject(callbackClass).extend(Stmt.loadVariable("this").invoke("getEventBus")).publicOverridesMethod("success", presenter).append(Stmt.loadVariable(presenter.getName()).invoke(name, Refs.get(parameter.getName()))).finish().finish();
    }

    private AnonymousClassStructureBuilder createProxy(MetaClass presenterKlass) {
        MetaClass proxyClass =
                parameterizedAs(ProxyImpl.class, typeParametersOf(presenterKlass));
        AnonymousClassStructureBuilder extend = Stmt.newObject(proxyClass).extend(presenterKlass);
        extend.getClassDefinition().setStatic(true);
        extend.getClassDefinition().setFinal(true);
        return extend;
    }
}