package com.feizi.framework.ioc.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.feizi.framework.ioc.bean.BeanDefinition;
import com.feizi.framework.ioc.utils.JsonUtils;

import java.io.InputStream;
import java.util.List;

/**
 * 上下文
 * Created by feizi on 2018/1/23.
 */
public class JsonApplicationContext extends BeanFactoryImpl {
    private String fileName;

    public JsonApplicationContext(String fileName){
        this.fileName = fileName;
    }

    /**
     * 初始化
     */
    public void init(){
        loadFile();
    }

    /**
     * 加载配置文件
     */
    private void loadFile(){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        List<BeanDefinition> beanDefinitionList = JsonUtils.readValue(inputStream, new TypeReference<List<BeanDefinition>>(){});
        if(null != beanDefinitionList && beanDefinitionList.size() > 0){
            for (BeanDefinition beanDefinition : beanDefinitionList){
                //注册bean
                registerBean(beanDefinition.getName(), beanDefinition);
            }
        }
    }
}
