package com.feizi.framework.ioc.entity;

/**
 * Created by feizi on 2018/1/23.
 */
public class Robot {
    private Hand hand;
    private Mouth mouth;

    public void show(){
        hand.waveHand();
        mouth.speak();
    }
}
