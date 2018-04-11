package com.feizi.framework.aop.core;

import com.feizi.framework.aop.advisor.TargetSource;
import com.feizi.framework.aop.interceptor.AopMethodInterceptor;
import com.feizi.framework.aop.invocation.CglibMethodInvocation;
import com.feizi.framework.aop.invocation.MethodInvocation;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by feizi on 2018/1/31.
 */
public class DynamicAdvisedInterceptor implements MethodInterceptor {

    protected final List<AopMethodInterceptor> aopMethodInterceptorList;
    protected final TargetSource targetSource;

    public DynamicAdvisedInterceptor(List<AopMethodInterceptor> aopMethodInterceptorList, TargetSource targetSource) {
        this.aopMethodInterceptorList = aopMethodInterceptorList;
        this.targetSource = targetSource;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        MethodInvocation methodInvocation = new CglibMethodInvocation(o, targetSource.getTargetObject(), method, objects, aopMethodInterceptorList, methodProxy);
        return methodInvocation.proceed();
    }
}
