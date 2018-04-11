package com.feizi.framework.aop;

import com.feizi.framework.aop.advisor.BeforeMethodAdvice;
import com.feizi.framework.aop.utils.ThreadLocalUtils;

import java.lang.reflect.Method;

/**
 * Created by feizi on 2018/2/1.
 */
public class StartTimeBeforeMethod implements BeforeMethodAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) {
        long startTime = System.currentTimeMillis();
        System.out.println("开始计时...");
        ThreadLocalUtils.set(startTime);
    }
}
