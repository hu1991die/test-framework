package com.feizi.framework.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;

import java.util.concurrent.Future;

/**
 * Created by feizi on 2018/2/24.
 */
public class CommandHelloWorld extends HystrixCommand<String> {
    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        // a real example would do work like a network call here
//        int i = 1 / 0;
        return "Hello " + name + "!";
    }

    /**
     * 执行服务降级，Hystrix会在run()执行过程中出现错误、超时、线程池拒绝，断路器熔断等情况时，
     * 执行getFallback()方法内的逻辑
     * @return
     */
    @Override
    protected String getFallback() {
        return "Failed";
    }

    public static void main(String[] args) {
        String s = new CommandHelloWorld("Bob").execute();
//        Future<String> s = new CommandHelloWorld("Bob").queue();
//        Observable<String> s = new CommandHelloWorld("Bob").observe();
//        Observable<String> s = new CommandHelloWorld("Bob").toObservable();
//        System.out.println(s);
    }
}
