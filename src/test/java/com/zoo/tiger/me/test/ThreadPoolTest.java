package com.zoo.tiger.me.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Tiger
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 不会直接抛出异常，需要future.get()后处理，也就是可以个性化处理异常了
        Future<?> future = executorService.submit(() -> {
            Integer b = null;
            int a = 1 / b;
        });

        try {
            Object o = future.get();
        } catch (Exception e) {
            throw e;
        }


        System.out.println("1234");
        System.out.println("1234");
        System.out.println("1234");
        System.out.println("1234");
        executorService.execute(() -> {
            Integer b = null;
            int a = 1/b;
        });
        Thread.sleep(1000);

        System.out.println("1234");
        System.out.println("1234");
        System.out.println("1234");
        System.out.println("1234");

        executorService.shutdown();
    }




}
