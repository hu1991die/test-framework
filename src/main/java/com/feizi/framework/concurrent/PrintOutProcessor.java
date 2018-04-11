package com.feizi.framework.concurrent;

import java.util.List;

/**
 * Created by feizi on 2018/2/1.
 */
public class PrintOutProcessor implements Processor<String>{
    @Override
    public void process(List<String> list) {
        System.out.println("start flush...");

        for (String str : list) {
            System.out.println(str);
        }

        System.out.println("end flush...");
    }
}
