package com.zoo.tiger.me.handcode;


import java.util.concurrent.locks.LockSupport;

class SelfThread extends Thread {
    private Object object;

    public SelfThread(Object object) {
        this.object = object;
    }
//    public SelfThread() {
//        object = new Thread();
//    }

    public void run() {
        System.out.println("before unpark");
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // 获取blocker
        System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));
        // 释放许可
        LockSupport.unpark((Thread) object);
        // 休眠500ms，保证先执行park中的setBlocker(t, null);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 再次获取blocker
        System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));

        System.out.println("after unpark");
    }
}

public class LockSupportDemo {
    public static void main(String[] args) {
        SelfThread selfThread = new SelfThread(Thread.currentThread());
//        SelfThread selfThread = new SelfThread();
        selfThread.start();
        try {
            // 主线程睡眠3s
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("before park");
        // 获取许可
        LockSupport.park("ParkAndUnparkDemo222");
        System.out.println("after park");
    }
}
