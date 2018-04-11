package com.feizi.framework.aop.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * Created by feizi on 2018/1/31.
 */
public final class ReflectionUtils {
    public static Object invokeMethodUseReflection(Object target, Method method, Object[] args){
        try {
            method.setAccessible(true);
            return method.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
