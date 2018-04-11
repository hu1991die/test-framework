package com.feizi.framework.ioc.core;

import com.feizi.framework.ioc.bean.BeanDefinition;
import com.feizi.framework.ioc.utils.Dom4jReadUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * XMl上下文
 * Created by feizi on 2018/1/29.
 */
public class XmlApplicationContext extends BeanFactoryImpl{
    private final static Logger LOGGER = LoggerFactory.getLogger(XmlApplicationContext.class);

    private String fileName;

    public XmlApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 初始化
     */
    public void init(){
        loadFile();
    }

    /**
     * 加载文件
     */
    private void loadFile(){
        try {
            List<BeanDefinition> beanDefinitionList = Dom4jReadUtils.parseXML(fileName);
            if(null != beanDefinitionList && beanDefinitionList.size() > 0){
                for (BeanDefinition beanDefinition : beanDefinitionList){
                    //注册bean
                    registerBean(beanDefinition.getName(), beanDefinition);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            LOGGER.error("Bean注册失败............");
        }
    }
}
