package com.feizi.framework.aop.advisor;

/**
 * Created by feizi on 2018/1/30.
 */
public class Advisor {
    private Advice advice;
    private Pointcut pointcut;

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public Pointcut getPointcut() {
        return pointcut;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }
}
