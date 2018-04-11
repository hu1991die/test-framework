package com.feizi.framework.ioc.core;

/**
 * Bean注入工厂
 * Created by feizi on 2018/1/23.
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;
}
