package com.feizi.framework.aop.interceptor;

import com.feizi.framework.aop.advisor.AfterRunningAdvice;
import com.feizi.framework.aop.invocation.MethodInvocation;

/**
 * 后置方法拦截器
 * Created by feizi on 2018/1/30.
 */
public class AfterRunningAdviceInterceptor implements AopMethodInterceptor {
    private AfterRunningAdvice advice;

    public AfterRunningAdviceInterceptor(AfterRunningAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object returnVal = methodInvocation.proceed();
        advice.after(returnVal, methodInvocation.getMethod(),methodInvocation.getArguments(), methodInvocation);
        return returnVal;
    }
}
