package com.feizi.framework.hystrix;

import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

/**
 * Created by feizi on 2018/3/1.
 */
public class CommandHelloWorldTest {

    /**
     * 测试同步执行
     */
    @Test
    public void testSynchronous(){
        System.out.println(new CommandHelloWorld("World").execute());
    }

    /**
     * 测试异步执行
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testAsynchronoue() throws ExecutionException, InterruptedException {
        Future<String> result = new CommandHelloWorld("World").queue();
        //异步执行用get()来获取结果
        System.out.println(result.get());
    }

    /**
     * 虽然HystrixCommand具备了observe()和toObservable()的功能，但是它的实现有一定的局限性
     * 它返回的Observable只能发射一次数据，所以Hystrix还提供了HystrixObservableCommand,
     * 通过它实现的命令可以获取能发多次的Observable
     */
    @Test
    public void testObserve(){
        Observable<String> ho = new CommandHelloWorld("World").observe();
        ho.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("==========onCompleted...");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("==========onError...");
                throwable.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("==========onNext..." + s);
            }
        });

        ho.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("===========call: " + s);
            }
        });
    }

    @Test
    public void testToObservable(){
        /**
         * Cold Observable在没有 “订阅者” 的时候并不会发布时间，
         * 而是进行等待，知道有 “订阅者” 之后才发布事件，所以对于
         * Cold Observable的订阅者，它可以保证从一开始看到整个操作的全部过程。
         */
        Observable<String> co = new CommandHelloWorld("World").toObservable();
        System.out.println(co.toBlocking().single());
    }
}