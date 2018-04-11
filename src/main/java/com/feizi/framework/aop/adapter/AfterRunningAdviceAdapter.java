package com.feizi.framework.aop.adapter;

import com.feizi.framework.aop.advisor.Advisor;
import com.feizi.framework.aop.advisor.AfterRunningAdvice;
import com.feizi.framework.aop.interceptor.AfterRunningAdviceInterceptor;
import com.feizi.framework.aop.interceptor.AopMethodInterceptor;

/**
 * Created by feizi on 2018/1/31.
 */
public class AfterRunningAdviceAdapter implements AdviceAdapter {

    private static final AfterRunningAdviceAdapter INSTANCE = new AfterRunningAdviceAdapter();

    private AfterRunningAdviceAdapter() {

    }

    public static AfterRunningAdviceAdapter getInstance(){
        return INSTANCE;
    }

    @Override
    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        AfterRunningAdvice advice = (AfterRunningAdvice) advisor.getAdvice();
        return new AfterRunningAdviceInterceptor(advice);
    }
}
