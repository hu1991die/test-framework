package com.feizi.framework.aop.invocation;

import java.lang.reflect.Method;

/**
 * 方法调用
 * Created by feizi on 2018/1/30.
 */
public interface MethodInvocation {
    Method getMethod();
    Object[] getArguments();
    Object proceed() throws Throwable;
}
