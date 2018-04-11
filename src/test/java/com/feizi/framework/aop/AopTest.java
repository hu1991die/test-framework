package com.feizi.framework.aop;

import com.feizi.framework.aop.core.AopApplicationContext;

/**
 * Created by feizi on 2018/2/1.
 */
public class AopTest {
    public static void main(String[] args) throws Exception {
        AopApplicationContext aopApplicationContext = new AopApplicationContext("aopApplication.json");
        aopApplicationContext.init();
        TestService testService = (TestService) aopApplicationContext.getBean("testServiceProxy");
        testService.testMethod();
    }
}
