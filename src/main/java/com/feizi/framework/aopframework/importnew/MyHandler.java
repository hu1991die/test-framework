package com.feizi.framework.aopframework.importnew;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by feizi on 2018/1/29.
 */
public class MyHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = method.invoke(proxy, args);
        return result;
    }
}
