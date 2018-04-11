package com.feizi.framework.aop.bean;

import com.feizi.framework.ioc.bean.BeanDefinition;

import java.util.List;

/**
 * Created by feizi on 2018/1/31.
 */
public class AopBeanDefinition extends BeanDefinition {
    private String target;
    private List<String> interceptorNames;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<String> getInterceptorNames() {
        return interceptorNames;
    }

    public void setInterceptorNames(List<String> interceptorNames) {
        this.interceptorNames = interceptorNames;
    }
}
