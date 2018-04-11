package com.feizi.framework.aopframework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by feizi on 2018/1/29.
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;
    private ProxyUtil proxyUtil;

    public CglibProxy(Object target) throws ClassNotFoundException {
        this.target = target;
        this.proxyUtil = new ProxyUtil();
    }

    public <T> T getProxy(){
        return (T) new Enhancer().create(this.target.getClass(), this);
    }

    public <T> T getProxy(Class<?> clazz){
        return (T) new Enhancer().create(clazz, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        ProxyEntity proxyEntity = new ProxyEntity(methodProxy, this.target.getClass(), o, method, objects);
        return proxyUtil.generateEntity(proxyEntity);
    }
}
