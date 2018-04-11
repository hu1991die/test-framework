package com.feizi.framework.ioc.utils;

import com.feizi.framework.ioc.bean.BeanDefinition;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 使用dom4j读取xml文件
 * Created by feizi on 2018/1/27.
 */
public final class Dom4jReadUtils {

    private static List<BeanDefinition> beanDefinitionList = new ArrayList<>();

    /**
     * 读取XML文件
     * @param fileName 文件路径
     * @return
     * @throws DocumentException
     */
    public static Document readXML(String fileName) throws DocumentException{
        //解析流
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        return document;
    }

    /**
     * 解析xml文件
     * @param fileName
     * @return
     * @throws DocumentException
     */
    public static List<BeanDefinition> parseXML(String fileName) throws DocumentException{
        Document document = readXML(fileName);
        Element root = document.getRootElement();
        if(null == root){
            return beanDefinitionList;
        }

        //获取bean节点下的子节点集合
        List<Element> nodes = root.elements("bean");
        if(null != nodes && nodes.size() > 0){
            BeanDefinition beanDefinition;
            // iterate through child elements of root
            for (Iterator<Element> iterator = nodes.iterator(); iterator.hasNext();){
                Element element = iterator.next();
                if(null != element){
                    //获取id属性值
                    String id = element.attributeValue("id");
                    //获取class属性值
                    String clazz = element.attributeValue("class");

                    //实例化beanDefinition
                    beanDefinition = new BeanDefinition(id, clazz);
                    beanDefinitionList.add(beanDefinition);
                }
            }
        }
        return beanDefinitionList;
    }

    public static void main(String[] args) {
        try {
            parseXML("application.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
