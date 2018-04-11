package com.feizi.framework.aop.interceptor;

import com.feizi.framework.aop.advisor.BeforeMethodAdvice;
import com.feizi.framework.aop.invocation.MethodInvocation;

/**
 * 前置方法拦截器
 * Created by feizi on 2018/1/30.
 */
public class BeforeMethodAdviceInterceptor implements AopMethodInterceptor {
    private BeforeMethodAdvice advice;

    public BeforeMethodAdviceInterceptor(BeforeMethodAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation);
        return methodInvocation.proceed();
    }
}
