package com.feizi.framework.aop.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.feizi.framework.aop.bean.AopBeanDefinition;
import com.feizi.framework.ioc.bean.BeanDefinition;
import com.feizi.framework.ioc.utils.ClassUtils;
import com.feizi.framework.ioc.utils.JsonUtils;

import java.io.InputStream;
import java.util.List;

/**
 * Created by feizi on 2018/1/31.
 */
public class AopApplicationContext extends AopBeanFactoryImpl {
    private String fileName;

    public AopApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    public void init(){
        loadFile();
    }

    private void loadFile(){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        List<AopBeanDefinition> aopBeanDefinitionList = JsonUtils.readValue(inputStream, new TypeReference<List<AopBeanDefinition>>(){});
        if(null != aopBeanDefinitionList && aopBeanDefinitionList.size() > 0){
            for (AopBeanDefinition aopBeanDefinition : aopBeanDefinitionList){
                Class<?> clazz = ClassUtils.loadClass(aopBeanDefinition.getClassName());
                if(clazz == ProxyFactoryBean.class){
                    registerBean(aopBeanDefinition.getName(), aopBeanDefinition);
                }else {
                    registerBean(aopBeanDefinition.getName(), (BeanDefinition)aopBeanDefinition);
                }
            }
        }
    }
}
