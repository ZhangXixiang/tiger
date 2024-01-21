package com.zoo.tiger.me.handcode;

/**
 * 实现一个单例
 */
public class Dog {

    /**
     * 私有化构造函数
     */
    private Dog(){}

    /**
     * volatile保证实例化对象的时候禁止指令重排序 基于happens-before规则：前一个操作对后一个操作可见
     * ①分配对象内存
     * ②初始化对象
     * ③将对象引用指向内存
     */
    private static volatile Dog dog = null;

    public Dog getDog() {
        if(null == dog){
            synchronized (Dog.class) {
                if(null == dog) {
                    dog = new Dog();
                }
            }
        }
        return dog;
    }
}
