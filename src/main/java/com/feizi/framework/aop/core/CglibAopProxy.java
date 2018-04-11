package com.feizi.framework.aop.core;

import com.feizi.framework.aop.advisor.AdvisedSupport;
import com.feizi.framework.ioc.utils.ClassUtils;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by feizi on 2018/1/31.
 */
public class CglibAopProxy implements AopProxy {

    private AdvisedSupport advisedSupport;
    private Object[] constructorArgs;
    private Class<?>[] constructorArgTypes;

    public CglibAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return getProxy(null);
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        Class<?> rootClass = advisedSupport.getTargetSource().getTargetClass();
        if(null == classLoader){
            classLoader = ClassUtils.getDefaultClassLoader();
        }

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(rootClass.getSuperclass());
        //增加拦截器的核心方法
        Callback callback = getCallback(advisedSupport);
        enhancer.setCallback(callback);
        enhancer.setClassLoader(classLoader);

        if(null != constructorArgs && constructorArgs.length > 0){
            return enhancer.create(constructorArgTypes, constructorArgs);
        }
        return enhancer.create();
    }

    private Callback getCallback(AdvisedSupport advisedSupport){
        return new DynamicAdvisedInterceptor(advisedSupport.getAopMethodInterceptorList(), advisedSupport.getTargetSource());
    }

    public AdvisedSupport getAdvisedSupport() {
        return advisedSupport;
    }

    public void setAdvisedSupport(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object[] getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(Object[] constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public Class<?>[] getConstructorArgTypes() {
        return constructorArgTypes;
    }

    public void setConstructorArgTypes(Class<?>[] constructorArgTypes) {
        this.constructorArgTypes = constructorArgTypes;
    }
}
