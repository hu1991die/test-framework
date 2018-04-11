package com.feizi.framework.aopframework;

/**
 * Created by feizi on 2018/1/29.
 */
@MyAspect   //标明这是一个切点类
public class Player {

    @Before("com.feizi.framework.aopframework.Music.sing()") //前置通知：在调用sing()方法之前调用
    public void beforeSing(){
        System.out.println("开始唱歌前的准备...");
    }

    @After("com.feizi.framework.aopframework.Music.sing()")  //后置通知：在调用sing()方法之后调用
    public void afterSing(){
        System.out.println("唱完之后开始评分...");
    }
}
