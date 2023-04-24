package com.zoo.tiger.me.spr;

import java.util.concurrent.CountDownLatch;

/**
 * @author Tiger
 */
public class StaticSonTest extends StaticParentTest {


    static {
        System.out.println("StaticSonTest-static");
    }

    {
        System.out.println("StaticSonTest-normal");
    }

    public StaticSonTest() {
        System.out.println("StaticSonTest-constract");
    }


    /**
     * 类的实例化顺序？
     * 父类中的static代码块，当前类的static代码块
     * 父类的普通代码块
     * 父类的构造函数
     * 当前类普通代码块
     * 当前类的构造函数
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        StaticSonTest bean = new StaticSonTest();
        System.out.println("init---");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();
    }
}
