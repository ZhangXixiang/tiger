package com.zoo.tiger.me.test.thread;

import com.zoo.tiger.me.exception.BizException;
import jdk.nashorn.internal.codegen.CompilerConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Tiger
 */
@Slf4j
public class ThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // executeTask1();
        // executeTask2();

        // executeTaskWithException1();
        executeTaskWithException2();
    }

    public static void executeTask1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(() -> log.info("in runnable!!!!"));
        Object o = future.get();
        executorService.shutdown();
    }

    public static void executeTask2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(() -> {
            log.info("in callable!!!!");
            return "callable111";
        });
        Object o = future.get();
        executorService.shutdown();
    }

    public static void executeTaskWithException1() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(() -> {
            log.info("in callable!!!!");
            throw new BizException(1, "a customer Exception");
        });
        try {
            Object object = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            e.getCause();
        }
        executorService.shutdown();
    }

    public static void executeTaskWithException2() throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "abc";
            }
        };
        FutureTask<String> future1 = new FutureTask<>(callable);
        // 使用thread，手动启动线程
        /*new Thread(future1).start();
        String s = future1.get();
        System.out.println("aaaa");*/

        // 直接将futuretask提交到线程池中
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(future1);

        String s = future1.get();
        System.out.println("aaaa");

    }


}
