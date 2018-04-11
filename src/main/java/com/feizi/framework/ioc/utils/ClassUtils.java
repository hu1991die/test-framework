package com.feizi.framework.ioc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 负责处理Java类的加载
 * Created by feizi on 2018/1/22.
 */
public final class ClassUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * 获取默认的类加载器
     * @return
     */
    public static ClassLoader getDefaultClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * @param className
     * @return
     */
    public static Class loadClass(String className){
        try {
            return getDefaultClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            LOGGER.error("加载类{}失败..., e:{}", className, e);
        }
        return null;
    }
}
