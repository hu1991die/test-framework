package com.feizi.framework.aop.advisor;

import java.lang.reflect.Method;

/**
 * Created by feizi on 2018/1/30.
 */
public interface AfterRunningAdvice extends Advice{
    Object after(Object returnVal, Method method, Object[] args, Object target);
}
