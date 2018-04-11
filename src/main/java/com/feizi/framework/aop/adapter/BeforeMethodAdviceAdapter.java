package com.feizi.framework.aop.adapter;

import com.feizi.framework.aop.advisor.Advisor;
import com.feizi.framework.aop.advisor.BeforeMethodAdvice;
import com.feizi.framework.aop.interceptor.AopMethodInterceptor;
import com.feizi.framework.aop.interceptor.BeforeMethodAdviceInterceptor;

/**
 * Created by feizi on 2018/1/31.
 */
public class BeforeMethodAdviceAdapter implements AdviceAdapter {

    private static final BeforeMethodAdviceAdapter INSTANCE = new BeforeMethodAdviceAdapter();

    private BeforeMethodAdviceAdapter() {
    }

    public static BeforeMethodAdviceAdapter getInstance(){
        return INSTANCE;
    }

    @Override
    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        BeforeMethodAdvice advice = (BeforeMethodAdvice) advisor.getAdvice();
        return new BeforeMethodAdviceInterceptor(advice);
    }
}
