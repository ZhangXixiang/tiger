package com.zoo.tiger.me.test.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Tiger
 */
public class MyRunnable implements Runnable{
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock writeLock = readWriteLock.writeLock();
    @Override
    public void run() {
        if (writeLock.tryLock()) {
            writeLock.lock();
            System.out.println(Thread.currentThread() + "我拿到写锁了");
            System.out.println(Thread.currentThread() + "睡眠10秒");

            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            writeLock.unlock();
        }
    }
}
