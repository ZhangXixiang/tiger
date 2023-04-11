package com.zoo.tiger.me.test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Tiger
 * VM config
 * -XX:+PrintGCDetails -Xms2048m -Xmx2048m
 */
public class ListTest {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        ArrayList<String> strings = new ArrayList<String>(1_000_000);

        for (int i = 0; i < 10_000_000; i++) {
            strings.add("a" + i);
        }
        System.out.println(System.currentTimeMillis() - start);


        long start1 = System.currentTimeMillis();

        LinkedList<String> stringsLinked = new LinkedList<String>();

        for (int i = 0; i < 100_000_00; i++) {
            stringsLinked.add("a" + i);
        }
        System.out.println(System.currentTimeMillis() - start1);
    }


}
