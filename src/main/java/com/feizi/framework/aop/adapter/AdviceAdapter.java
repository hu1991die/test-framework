package com.feizi.framework.aop.adapter;

import com.feizi.framework.aop.advisor.Advisor;
import com.feizi.framework.aop.interceptor.AopMethodInterceptor;

/**
 * 通知适配器
 * Created by feizi on 2018/1/31.
 */
public interface AdviceAdapter {
    AopMethodInterceptor getInterceptor(Advisor advisor);
}
