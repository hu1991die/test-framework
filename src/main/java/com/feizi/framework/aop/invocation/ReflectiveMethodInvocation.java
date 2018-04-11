package com.feizi.framework.aop.invocation;

import com.feizi.framework.aop.interceptor.AopMethodInterceptor;
import com.feizi.framework.aop.utils.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by feizi on 2018/1/31.
 */
public class ReflectiveMethodInvocation implements ProxyMethodInvocation {
    protected final Object proxy;
    protected final Object target;
    protected final Method method;
    /*参数集合数组*/
    protected Object[] arguments = new Object[0];
    /*存储所有的拦截器*/
    protected final List<AopMethodInterceptor> interceptorList;
    /*当前拦截器的索引位置*/
    private int currentInterceptorIndex = -1;

    public ReflectiveMethodInvocation(Object proxy, Object target, Method method, List<AopMethodInterceptor> interceptorList, Object[] arguments) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.interceptorList = interceptorList;
        this.arguments = arguments;
    }

    @Override
    public Object getProxy() {
        return proxy;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        /*执行完所有的拦截器后，执行目标方法*/
        if(currentInterceptorIndex == this.interceptorList.size() - 1){
            return invokeOriginal();
        }

        /*迭代的执行拦截器，我们实现的拦截器都会执行MethodInvocation.proceed()*/
        AopMethodInterceptor aopMethodInterceptor = interceptorList.get(++currentInterceptorIndex);
        return aopMethodInterceptor.invoke(this);
    }

    protected Object invokeOriginal() throws Throwable{
        return ReflectionUtils.invokeMethodUseReflection(target, method, arguments);
    }
}
