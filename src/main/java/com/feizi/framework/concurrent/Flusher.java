package com.feizi.framework.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by feizi on 2018/2/1.
 */
public class Flusher<Item> {

    private final FlushThread<Item>[] flushThreads;

    private AtomicInteger index;

    /*防止多个线程同时执行，增加一个随机数间隔*/
    private static final Random r = new Random();

    private static final int delta = 50;

    private static ScheduledExecutorService TIMER = new ScheduledThreadPoolExecutor(1);
    private static ExecutorService POOL = Executors.newCachedThreadPool();

    public Flusher(String name, int bufferSize, int flushInterval, int queueSize, int threads, Processor<Item> processor){
        this.flushThreads = new FlushThread[threads];

        if(threads > 1){
            index = new AtomicInteger();
        }

        for (int i = 0; i < threads; i++){
            final FlushThread<Item> flushThread = new FlushThread<Item>(name + "-" + i, bufferSize, processor, flushInterval, queueSize);
            flushThreads[i] = flushThread;
            POOL.submit(flushThread);

            //定时调用timeout方法
            TIMER.scheduleAtFixedRate(flushThread, r.nextInt(delta), flushInterval, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 对index取模， 保证多线程都能被add
     * @param item
     * @return
     */
    public boolean add(Item item){
        int len = flushThreads.length;
        if(len == 1){
            return flushThreads[0].add(item);
        }

        int mod = index.incrementAndGet() % len;
        return flushThreads[mod].add(item);
    }

    private static class FlushThread<Item> implements Runnable{

        /*名称*/
        private final String name;
        /*队列大小*/
        private final int bufferSize;
        /*操作时间间隔*/
        private int flushInterval;

        /*上一次提交的时间*/
        private volatile long lastFlushTime;
        /*写线程*/
        private volatile Thread writer;

        /*持有数据的阻塞队列*/
        private final BlockingQueue<Item> queue;
        /*达成条件后具体执行的方法*/
        private final Processor<Item> processor;

        /*构造函数*/
        public FlushThread(String name, int bufferSize, Processor<Item> processor, int flushInterval, int queueSize) {
            this.name = name;
            this.bufferSize = bufferSize;
            this.processor = processor;
            this.flushInterval = flushInterval;

            this.lastFlushTime = System.currentTimeMillis();
            this.queue = new ArrayBlockingQueue<Item>(queueSize);
        }

        //外部提交数据的方法
        public boolean add(Item item){
            boolean result = queue.offer(item);
            flushOnDemand();
            return result;
        }

        /**
         * 提供给外部的超时方法
         */
        public void timeOut(){
            //超过两次提交，并且间隔时间超过指定间隔
            if(System.currentTimeMillis() - lastFlushTime >= flushInterval){
                start();
            }
        }

        /**
         * 解除线程的阻塞
         */
        private void start(){
            LockSupport.unpark(writer);
        }

        /**
         * 当前的数据是否大于提交的条件
         */
        private void flushOnDemand(){
            if(queue.size() >= bufferSize){
                start();
            }
        }

        /**
         * 执行提交数据的方法
         */
        public void flush(){
            lastFlushTime = System.currentTimeMillis();
            List<Item> temp = new ArrayList<>(bufferSize);
            int size = queue.drainTo(temp, bufferSize);
            if(size > 0){
                try {
                    processor.process(temp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 根据数据的数量和提交的时间间隔判断是否可以执行提交
         * @return
         */
        private boolean canFlush(){
            return (queue.size() > bufferSize) || (System.currentTimeMillis() - lastFlushTime > flushInterval);
        }

        @Override
        public void run() {
            writer = Thread.currentThread();
            writer.setName(name);

            while (!writer.isInterrupted()){
                while (!canFlush()){
                    //如果线程没有被打断，且也没达到执行的条件，则阻塞线程
                    LockSupport.park(this);
                }
                flush();
            }
        }
    }
}
