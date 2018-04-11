package com.feizi.framework.aop.interceptor;

import com.feizi.framework.aop.invocation.MethodInvocation;

/**
 * AOP方法拦截器
 * Created by feizi on 2018/1/30.
 */
public interface AopMethodInterceptor {
    Object invoke(MethodInvocation methodInvocation) throws Throwable;
}
