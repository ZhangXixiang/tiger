package com.zoo.tiger.me.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Tiger
 */
@Slf4j
public class TreadTest {

    private static volatile int i = 0;

    public static void main(String[] args) {
        // 测试IDEA 针对线程进行断点
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int k = 0; k < 100; k++) {
                    i++;
                }
                log.info("thread");
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 100; j++) {
                    i++;
                }
                log.info("thread1");
            }
        });
        thread.start();
        thread1.start();
    }

}

