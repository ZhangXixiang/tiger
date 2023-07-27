package com.zoo.tiger;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

public class SingletonTest {

    // 构造方法私有
    private SingletonTest() {
    }

    // 饿汉
    private static SingletonTest item = new SingletonTest();


    public SingletonTest getItem() {
        return item;
    }

    // 懒汉
    private volatile SingletonTest obj;

    public SingletonTest getObj() {
        if (null == obj) {
            synchronized (this) {
                if (null == obj) {
                    obj = new SingletonTest();
                }
            }
        }
        return obj;
    }

    // 静态内部类
    static class Item {
        private static final SingletonTest a = new SingletonTest();
    }
    public SingletonTest getSingle(){
        return SingletonTest.Item.a;
    }

    // 基于枚举
    private enum SingleEnum {
        INSTANCE;

        private SingletonTest ins;

        SingleEnum() {
            ins = new SingletonTest();
        }

        private SingletonTest getInstance(){
            return ins;
        }
    }

}
