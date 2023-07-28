package com.zoo.tiger;


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



    private enum Single{
       ;

       private SingletonTest item;

        Single() {
            this.item = new SingletonTest();
        }

        public SingletonTest getIns() {
            return item;
        }
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
