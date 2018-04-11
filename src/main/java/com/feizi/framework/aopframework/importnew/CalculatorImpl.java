package com.feizi.framework.aopframework.importnew;

/**
 * Created by feizi on 2018/1/29.
 */
public class CalculatorImpl implements Calculator {
    @Override
    public int calculate(int a, int b) {
        return a / b;
    }
}
