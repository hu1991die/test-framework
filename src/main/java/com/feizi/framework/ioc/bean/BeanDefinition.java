package com.feizi.framework.ioc.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Bean实例定义
 * Created by feizi on 2018/1/20.
 */
public class BeanDefinition implements Serializable {
    private static final long serialVersionUID = -5942505261259536410L;

    /*Bean的名称*/
    private String name;
    /*Bean对应的Class类名称*/
    private String className;
    /*Bean对应的interface接口名称*/
    private String interfaceName;
    /*Bean对应的构造器列表*/
    private List<ConstructorArg> constructorArgList;
    /*Bean对应的属性列表*/
    private List<PropertyArg> propertyArgList;

    public BeanDefinition() {
    }

    public BeanDefinition(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<ConstructorArg> getConstructorArgList() {
        return constructorArgList;
    }

    public void setConstructorArgList(List<ConstructorArg> constructorArgList) {
        this.constructorArgList = constructorArgList;
    }

    public List<PropertyArg> getPropertyArgList() {
        return propertyArgList;
    }

    public void setPropertyArgList(List<PropertyArg> propertyArgList) {
        this.propertyArgList = propertyArgList;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", constructorArgList=" + constructorArgList +
                ", propertyArgList=" + propertyArgList +
                '}';
    }
}
