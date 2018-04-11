package com.feizi.framework.ioc;

import com.feizi.framework.ioc.core.JsonApplicationContext;
import com.feizi.framework.ioc.core.XmlApplicationContext;
import com.feizi.framework.ioc.entity.Robot;

/**
 * Created by feizi on 2018/1/23.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        /*JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");
        applicationContext.init();
        Robot robot = (Robot) applicationContext.getBean("robot");
        robot.show();*/

        /**
         * IOC实现步骤：
         * 1、初始化IOC容器
         * 2、读取配置文件
         * 3、将配置文件信息转换成容器能够识别的数据结构（这个数据结构在spring中叫做BeanDefinition）
         * 4、分析数据结构依次实例化相应的对象
         * 5、注入对象之间的依赖关系
         */
        XmlApplicationContext applicationContext = new XmlApplicationContext("application.xml");
        applicationContext.init();
        Robot robot = (Robot) applicationContext.getBean("robot");
        robot.show();
    }
}
