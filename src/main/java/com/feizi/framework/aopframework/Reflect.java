package com.feizi.framework.aopframework;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射类
 * Created by feizi on 2018/1/29.
 */
public class Reflect {
    //存入的是方法名以及其注解
    private Map<String, String> map;
    private Map<String, String> clazzMap;

    public Reflect() throws ClassNotFoundException {
        map = new HashMap<>();
        clazzMap = new HashMap<>();
        getAnnotationClass();
    }

    public Map<String, String> getMap(){
        //这里返回的是已经全部存好的map，方便proxyUtil使用
        return map;
    }

    public void getAnnotationClass() throws ClassNotFoundException {
        String className = "com.feizi.framework.aopframework.Player";
        // 这里为了省事直接动态加载了该类
        Class<?> clazz = Class.forName(className, false, Thread.currentThread().getContextClassLoader());

        //假设是注解类
        if(clazz.isAnnotationPresent(MyAspect.class)){
            //遍历方法
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods){
                //获取注解
                if (method.isAnnotationPresent(Before.class)){
                    Before before = method.getAnnotation(Before.class);
                    //获取注解的值以及当前类的名字调用方法
                    String beforeValue = before.value();
                    //存入的方法名，注解名，以及执行顺序
                    map.put(method.getName() + "-" + className + "-before", beforeValue.substring(0, beforeValue.length() - 2));
                } else if (method.isAnnotationPresent(After.class)){
                    After after = method.getAnnotation(After.class);
                    //获取注解的值以及当前类的名字调用方法
                    String afterValue = after.value();
                    map.put(method.getName() + "-" + className + "-after", afterValue.substring(0, afterValue.length() - 2));
                }
            }
        }
    }
}
