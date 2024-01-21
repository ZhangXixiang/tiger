package com.zoo.tiger.me.handcode;

class MyThread extends Thread {

    public void run() {
        synchronized (this) {
            System.out.println("before notify");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            notify();
            System.out.println("after notify");
        }
    }
}

public class WaitAndNotifyDemo {
    public static void main(String[] args) throws InterruptedException {
        MyThread selfThread = new MyThread();
        synchronized (selfThread) {
            try {
                selfThread.start();
                // 主线程睡眠3s
                Thread.sleep(0);
                System.out.println("before wait");
                // 阻塞主线程
                selfThread.wait();
                Thread.sleep(2000);
                System.out.println("after wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
