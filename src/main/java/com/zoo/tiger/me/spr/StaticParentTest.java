package com.zoo.tiger.me.spr;

/**
 * @author Tiger
 */
public class StaticParentTest {

    static {
        System.out.println("StaticParentTest-static");
    }

    {
        System.out.println("StaticParentTest-normal");
    }

    public StaticParentTest() {
        System.out.println("StaticParentTest-constract");
    }

}
