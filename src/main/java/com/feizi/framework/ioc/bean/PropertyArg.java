package com.feizi.framework.ioc.bean;

import java.io.Serializable;

/**
 * 属性定义
 * Created by feizi on 2018/1/20.
 */
public class PropertyArg implements Serializable {
    private static final long serialVersionUID = 3645608004372550467L;

    /*属性名称*/
    private String name;
    /*属性值*/
    private String value;
    /*属性类型名称*/
    private String typeName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "PropertyArg{" +
                "typeName='" + typeName + '\'' +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
