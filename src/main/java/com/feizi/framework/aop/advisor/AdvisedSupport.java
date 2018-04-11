package com.feizi.framework.aop.advisor;

import com.feizi.framework.aop.interceptor.AopMethodInterceptor;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by feizi on 2018/1/30.
 */
public class AdvisedSupport extends Advisor {
    //目标对象
    private TargetSource targetSource;
    //拦截器列表
    private List<AopMethodInterceptor> aopMethodInterceptorList = new LinkedList<>();

    public void addAopMethodInterceptor(AopMethodInterceptor interceptor){
        aopMethodInterceptorList.add(interceptor);
    }

    public void addAopMethodInterceptors(List<AopMethodInterceptor> interceptors){
        aopMethodInterceptorList.addAll(interceptors);
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public List<AopMethodInterceptor> getAopMethodInterceptorList() {
        return aopMethodInterceptorList;
    }

    public void setAopMethodInterceptorList(List<AopMethodInterceptor> aopMethodInterceptorList) {
        this.aopMethodInterceptorList = aopMethodInterceptorList;
    }
}
