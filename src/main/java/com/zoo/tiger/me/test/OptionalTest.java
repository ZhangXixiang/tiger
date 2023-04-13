package com.zoo.tiger.me.test;

import java.util.Optional;

/**
 * @author Tiger
 */
public class OptionalTest {

    public static void main(String[] args) {
        // String str = null;
        String str = "333";
        System.out.println(Optional.ofNullable(str).orElse("123"));
    }

}
