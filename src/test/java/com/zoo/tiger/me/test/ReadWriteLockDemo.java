package com.zoo.tiger.me.test;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Tiger
 */
public class ReadWriteLockDemo {
    private static final int THREAD_COUNT = 5;
    private static final int READ_THREAD_COUNT = 3;
    private static final int WRITE_THREAD_COUNT = 2;

    private static ReadWriteLock lock = new ReentrantReadWriteLock();
    private static int sharedData = 0;

    public static void main(String[] args) {
        for (int i = 0; i < READ_THREAD_COUNT; i++) {
            Thread readerThread = new Thread(new Reader());
            readerThread.start();
        }

        for (int i = 0; i < WRITE_THREAD_COUNT; i++) {
            Thread writerThread = new Thread(new Writer());
            writerThread.start();
        }
    }

    static class Reader implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < THREAD_COUNT; i++) {
                lock.readLock().lock(); // 获取读锁
                try {
                    System.out.println(
                        "Reader Thread " + Thread.currentThread().getId() + " reads sharedData: " + sharedData);
                } finally {
                    lock.readLock().unlock(); // 释放读锁
                }

                try {
                    Thread.sleep(1000); // 模拟读操作的耗时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Writer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < THREAD_COUNT; i++) {
                lock.writeLock().lock(); // 获取写锁
                try {
                    sharedData++;
                    System.out.println(
                        "Writer Thread " + Thread.currentThread().getId() + " writes sharedData: " + sharedData);
                } finally {
                    lock.writeLock().unlock(); // 释放写锁
                }

                try {
                    Thread.sleep(100); // 模拟写操作的耗时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
