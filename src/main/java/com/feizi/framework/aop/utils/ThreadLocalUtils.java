package com.feizi.framework.aop.utils;

/**
 * Created by feizi on 2018/2/1.
 */
public final class ThreadLocalUtils {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static long get(){
        if(null == threadLocal){
            throw new IllegalStateException("ThreadLocal...");
        }
        return threadLocal.get();
    }

    public static void set(long startTime){
        threadLocal.set(startTime);
    }

    public static void remove(){
        threadLocal.remove();
    }
}
