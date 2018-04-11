package com.feizi.framework.aopframework;

/**
 * Created by feizi on 2018/1/29.
 */
public class CglibTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Music music = new Music();
        CglibProxy cglibProxy = new CglibProxy(music);
        ((Music)cglibProxy.getProxy()).sing("飞子");
    }
}
