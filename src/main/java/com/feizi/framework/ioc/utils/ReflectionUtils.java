package com.feizi.framework.ioc.utils;

import java.lang.reflect.Field;

/**
 * 通过 Java 的反射原理来完成对象的依赖注入
 * Created by feizi on 2018/1/22.
 */
public final class ReflectionUtils {
    public static void injectField(Field field, Object obj, Object val) throws IllegalAccessException {
        if(null != field){
            field.setAccessible(true);
            field.set(obj, val);
        }
    }
}
