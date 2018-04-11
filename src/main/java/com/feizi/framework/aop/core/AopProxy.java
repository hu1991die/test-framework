package com.feizi.framework.aop.core;

/**
 * Created by feizi on 2018/1/31.
 */
public interface AopProxy {
    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
