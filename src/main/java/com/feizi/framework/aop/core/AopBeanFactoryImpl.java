package com.feizi.framework.aop.core;

import com.feizi.framework.aop.adapter.AfterRunningAdviceAdapter;
import com.feizi.framework.aop.adapter.BeforeMethodAdviceAdapter;
import com.feizi.framework.aop.advisor.Advice;
import com.feizi.framework.aop.advisor.AdvisedSupport;
import com.feizi.framework.aop.advisor.Advisor;
import com.feizi.framework.aop.advisor.AfterRunningAdvice;
import com.feizi.framework.aop.advisor.BeforeMethodAdvice;
import com.feizi.framework.aop.advisor.TargetSource;
import com.feizi.framework.aop.bean.AopBeanDefinition;
import com.feizi.framework.aop.interceptor.AopMethodInterceptor;
import com.feizi.framework.ioc.core.BeanFactoryImpl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by feizi on 2018/1/31.
 */
public class AopBeanFactoryImpl extends BeanFactoryImpl {
    private final static ConcurrentHashMap<String, AopBeanDefinition> aopBeanDefinitionMap = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<String, Object> aopBeanMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String name) throws Exception {
        Object aopBean = aopBeanMap.get(name);
        if(null != aopBean){
            return aopBean;
        }

        if(aopBeanDefinitionMap.containsKey(name)){
            AopBeanDefinition aopBeanDefinition = aopBeanDefinitionMap.get(name);
            AdvisedSupport advisedSupport = getAdvisedSupport(aopBeanDefinition);
            aopBean = new CglibAopProxy(advisedSupport).getProxy();
            aopBeanMap.put(name, aopBean);
            return aopBean;
        }
        return super.getBean(name);
    }

    protected void registerBean(String name, AopBeanDefinition aopBeanDefinition){
        aopBeanDefinitionMap.put(name, aopBeanDefinition);
    }

    private AdvisedSupport getAdvisedSupport(AopBeanDefinition aopBeanDefinition) throws Exception {
        AdvisedSupport advisedSupport = new AdvisedSupport();
        List<String> interceptorNames = aopBeanDefinition.getInterceptorNames();
        if(null != interceptorNames && interceptorNames.size() > 0){
            for (String interceptorName : interceptorNames){
                Advice advice = (Advice) getBean(interceptorName);
                Advisor advisor = new Advisor();
                advisor.setAdvice(advice);

                if(advice instanceof BeforeMethodAdvice){
                    AopMethodInterceptor interceptor = BeforeMethodAdviceAdapter.getInstance().getInterceptor(advisor);
                    advisedSupport.addAopMethodInterceptor(interceptor);
                }

                if(advice instanceof AfterRunningAdvice){
                    AopMethodInterceptor interceptor = AfterRunningAdviceAdapter.getInstance().getInterceptor(advisor);
                    advisedSupport.addAopMethodInterceptor(interceptor);
                }
            }
        }

        TargetSource targetSource = new TargetSource();
        Object object = getBean(aopBeanDefinition.getTarget());
        targetSource.setTargetClass(object.getClass());
        targetSource.setTargetObject(object);
        advisedSupport.setTargetSource(targetSource);
        return advisedSupport;
    }
}
