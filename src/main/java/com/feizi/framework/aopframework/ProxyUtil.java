package com.feizi.framework.aopframework;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 代理工具类
 * Created by feizi on 2018/1/29.
 */
public class ProxyUtil {
    private Reflect reflect;

    public ProxyUtil() throws ClassNotFoundException {
        this.reflect = new Reflect();
    }

    public void getMethod(String name){
        Map<String, String> map = new HashMap<>();
    }

    /**
     *
     * @param proxyEntity
     * @return
     * @throws Exception
     */
    public Object generateEntity(ProxyEntity proxyEntity) throws Throwable {
        String proxyMethodValue = proxyEntity.getMethod().toString().substring(proxyEntity.getMethod().toString().lastIndexOf(" ") + 1, proxyEntity.getMethod().toString().indexOf("("));
        Map<String, String> methodMap = reflect.getMap();

        for (Map.Entry<String, String> map : methodMap.entrySet()){
            if(map.getValue().equals(proxyMethodValue)){
                String[] str = mapKeyDivision(map.getKey());
                if("before".equals(str[2])){
                    //加载该类
                    Class<?> clazz = Class.forName(str[1], false, Thread.currentThread().getContextClassLoader());
                    Method method = clazz.getDeclaredMethod(str[0]);
                    //反射调用方法
                    method.invoke(clazz.newInstance(), null);
                }
            }
        }
        return doAfter(proxyEntity, methodMap);
    }

    private Object doAfter(ProxyEntity proxyEntity, Map<String, String> map) throws Throwable {
        Object object = proxyEntity.getMethodProxy().invokeSuper(proxyEntity.getObject(), proxyEntity.getArgs());
        String proxyMethodValue = proxyEntity.getMethod().toString().substring(proxyEntity.getMethod().toString().lastIndexOf(" ") + 1, proxyEntity.getMethod().toString().indexOf("("));
        for (Map.Entry<String, String> aMap : map.entrySet()){
            if(aMap.getValue().equals(proxyMethodValue)){
                String[] str = mapKeyDivision(aMap.getKey());
                if("after".equals(str[2])){
                    // 加载该类
                    Class<?> clazz = Class.forName(str[1], false, Thread.currentThread().getContextClassLoader());
                    Method method = clazz.getDeclaredMethod(str[0]);
                    // 这一步需要原始的类
                    method.invoke(clazz.newInstance(), null);
                }
            }
        }
        return object;
    }

    private String[] mapKeyDivision(String value){
        String[] str = new String[10];
        //注解下面的方法
        str[0] = value.substring(0, value.indexOf("-"));
        //注解所在的类
        str[1] = value.substring(value.indexOf("-") + 1, value.lastIndexOf("-"));
        //判断是before还是after
        str[2] = value.substring(value.lastIndexOf("-") + 1, value.length());
        return str;
    }
}
