package com.feizi.framework.ioc.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * 通过CGLIB代理技术处理对象的实例化
 * Created by feizi on 2018/1/22.
 */
public final class BeanUtils {
    public static <T> T instanceByCglib(Class<T> tClass, Constructor constructor, Object[] args){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(tClass);
        enhancer.setCallback(NoOp.INSTANCE);
        if(null == constructor){
            return (T) enhancer.create();
        }else {
            return (T) enhancer.create(constructor.getParameterTypes(), args);
        }
    }
}
