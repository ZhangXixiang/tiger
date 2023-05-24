package com.zoo.tiger.me.test.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Tiger
 */
public class MyThread extends Thread{
    // 错误示范 写在实例变量中的 每个线程都是不一样的lock 😭
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock = readWriteLock.readLock();
    @Override
    public void run() {
        if (readLock.tryLock()) {
            System.out.println(Thread.currentThread() + "我拿到读锁了");
            System.out.println(Thread.currentThread() + "睡眠10秒");

            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
