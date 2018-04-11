package com.feizi.framework.ioc.core;

import com.feizi.framework.ioc.bean.BeanDefinition;
import com.feizi.framework.ioc.bean.ConstructorArg;
import com.feizi.framework.ioc.utils.BeanUtils;
import com.feizi.framework.ioc.utils.ClassUtils;
import com.feizi.framework.ioc.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean注入工厂实现类
 * Created by feizi on 2018/1/23.
 */
public class BeanFactoryImpl implements BeanFactory {

    private final static Logger LOGGER = LoggerFactory.getLogger(BeanFactoryImpl.class);

    /*装载Bean实例对象*/
    private final static ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();
    /*Bean实例对应的类映射,Bean对象与对应的数据结构*/
    private final static ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private final static Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<String>());

    @Override
    public Object getBean(String name) throws Exception {
        //查找对象是否已经实例化过
        Object bean = beanMap.get(name);
        if(null != bean){
            //如果先前已经实例化过，则直接返回Bean实例对象即可
            return bean;
        }

        /*如果没有实例化过，则需要调用createBean方法创建对象*/
        bean = createBean(beanDefinitionMap.get(name));
        if(null != bean){
            /*对象实例化成功之后，注入对象所需要的参数及属性*/
            populateBean(bean);

            /*然后再把对象存入Map中方便下次使用*/
            beanMap.put(name, bean);
        }

        return bean;
    }

    /**
     * 注册Bean对象
     * @param name Bean名称
     * @param beanDefinition Bean定义映射关系
     */
    protected void registerBean(String name, BeanDefinition beanDefinition){
        beanDefinitionMap.put(name, beanDefinition);
        beanNameSet.add(name);
    }

    /**
     * 创建Bean实例对象
     * @param beanDefinition Bean实例定义
     * @return
     * @throws Exception
     */
    private Object createBean(BeanDefinition beanDefinition) throws Exception{
        /*获取ClassName名称*/
        String className = beanDefinition.getClassName();
        Class clazz = ClassUtils.loadClass(className);
        if(null == clazz){
            throw new Exception("can not find bean by className");
        }

        List<ConstructorArg> constructorArgList = beanDefinition.getConstructorArgList();
        if(null != constructorArgList && constructorArgList.size() > 0){
            List<Object> objectList = new ArrayList<>();
            for (ConstructorArg constructorArg : constructorArgList){
                objectList.add(getBean(constructorArg.getRef()));
            }
            return BeanUtils.instanceByCglib(clazz, clazz.getConstructor(), objectList.toArray());
        }

        return BeanUtils.instanceByCglib(clazz, null, null);
    }

    /**
     * 注入Bean对象依赖的属性和参数
     * @param bean Bean实例对象
     * @throws Exception
     */
    private void populateBean(Object bean) throws Exception{
        Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
        if(null != fields && fields.length > 0){
            for (Field field : fields){
                String beanName = field.getName();
                beanName = StringUtils.uncapitalize(beanName);

                if(beanNameSet.contains(beanName)){
                    Object fieldBean = getBean(beanName);
                    if(null != fieldBean){
                        ReflectionUtils.injectField(field, bean, fieldBean);
                    }
                }
            }
        }
    }
}
