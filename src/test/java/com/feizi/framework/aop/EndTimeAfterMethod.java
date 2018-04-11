package com.feizi.framework.aop;

import com.feizi.framework.aop.advisor.AfterRunningAdvice;
import com.feizi.framework.aop.utils.ThreadLocalUtils;

import java.lang.reflect.Method;

/**
 * Created by feizi on 2018/2/1.
 */
public class EndTimeAfterMethod implements AfterRunningAdvice {
    @Override
    public Object after(Object returnVal, Method method, Object[] args, Object target) {
        long endTime = System.currentTimeMillis();
        long startTime = ThreadLocalUtils.get();
        ThreadLocalUtils.remove();
        System.out.println("方法耗时： " + (endTime - startTime) + "ms");
        return returnVal;
    }
}
