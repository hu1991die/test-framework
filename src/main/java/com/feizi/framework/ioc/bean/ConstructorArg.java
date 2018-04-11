package com.feizi.framework.ioc.bean;

import java.io.Serializable;

/**
 * 参数定义
 * Created by feizi on 2018/1/20.
 */
public class ConstructorArg implements Serializable {
    private static final long serialVersionUID = 7507439002270463557L;

    /*参数索引*/
    private int index;
    /*参数引用*/
    private String ref;
    /*参数名称*/
    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ConstructorArg{" +
                "index=" + index +
                ", ref='" + ref + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
