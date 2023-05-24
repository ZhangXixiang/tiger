package com.zoo.tiger.me.test.thread;

import java.util.concurrent.Callable;

/**
 * @author Tiger
 */
public class MyCallable implements Callable {

    @Override
    public Object call() throws Exception {
        return "hello";

    }
}
