package com.feizi.framework.aop.advisor;

/**
 * Created by feizi on 2018/1/30.
 */
public class TargetSource {
    private Class<?> targetClass;
    private Object targetObject;

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }
}
