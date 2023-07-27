package com.zoo.tiger.me.test;

import com.zoo.tiger.me.model.People;

import java.util.Arrays;

public class CloneTest {

    /**
     * Arrays.copyOf
     * @param args
     */
    public static void main(String[] args) {
        int a[] = {1, 2};
        int b[] = Arrays.copyOf(a, a.length);
        b[0] = 10;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

        Integer a1[] = {1, 2};
        Integer b1[] = Arrays.copyOf(a1, a1.length);
        a1[0] = 10;
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(b1));

        People[] o1 = {new People(1, "Java")};
        People[] o2 = Arrays.copyOf(o1, o1.length);
        // 修改原型对象的第一个元素的值
        o1[0].setName("Jdk");
        System.out.println("o1:" + o1[0].getName());
        System.out.println("o2:" + o2[0].getName());

    }
}
