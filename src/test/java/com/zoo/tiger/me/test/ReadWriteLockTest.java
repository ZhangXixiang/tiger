package com.zoo.tiger.me.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.zoo.tiger.me.test.thread.MyCallable;

/**
 * 读写锁
 * 
 * @author Tiger
 */
public class ReadWriteLockTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // run方法是普通方法 start才是启动线程
        /*MyThread myThread1 = new MyThread();
        myThread1.run();
        MyThread myThread2 = new MyThread();
        myThread2.run();*/

        /*MyThread myThread1 = new MyThread();
        myThread1.start();
        MyThread myThread2 = new MyThread();
        myThread2.start();*/

        // 这个run方法也是普通方法 哈哈哈
        /*MyRunable myRunnable3 = new MyRunable();
        myRunnable3.run();
        MyRunable myRunable4 = new MyRunable();
        myRunable4.run();*/

        /*MyRunnable myRunnable3 = new MyRunnable();
        Thread myThread3 = new Thread(myRunnable3);
        myThread3.start();
        MyRunnable myRunnable4 = new MyRunnable();
        Thread thread4 = new Thread(myRunnable4);
        thread4.start();*/

        MyCallable myCallable = new MyCallable();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(myCallable);
        System.out.println(future.get());
        executorService.shutdown();

    }

}
