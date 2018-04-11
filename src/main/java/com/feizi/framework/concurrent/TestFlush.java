package com.feizi.framework.concurrent;

/**
 * Created by feizi on 2018/2/1.
 */
public class TestFlush {
    public static void main(String[] args) throws InterruptedException {
        Flusher<String> stringFlusher = new Flusher<>("test", 5, 1000, 30, 1, new PrintOutProcessor());

        int index = 1;
        while (true){
            stringFlusher.add(String.valueOf(index++));
//            Thread.sleep(10000);
        }
    }
}
