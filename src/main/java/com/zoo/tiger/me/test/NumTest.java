package com.zoo.tiger.me.test;

import java.util.Arrays;

/**
 * @author Tiger
 */
public class NumTest {

    public static void main(String[] args) {
        int a = 'A';
        System.out.println("a=" + a);
        char b = (char) 97;
        System.out.println("b=" + b);

        long c = 100;
        int d = (int) c;
        // = '\u0000'
        char defaultChar = '\u0000';
        System.out.println(defaultChar);

        short e = 10;
        short f = 10;
        e = (short) (e + f);
        System.out.println("e=" + e);

        String[] g = {"1", "2"};
        System.out.println(g);
        System.out.println(Arrays.toString(g));

        String str = "hello, world!";
        System.out.println(str.substring(0, 5));// hello
        System.out.println(str.substring(7));// world!


        String h = new String("h4");
        String i = h.intern();
        System.out.println(h == i);

        String j = new String("h") + new String("h");
        String k = j.intern();
        System.out.println(j == k);

        String s1 = new String("⼆哥") + new String("三妹");
        String s2 = s1.intern();
        System.out.println(s1 == s2);

        String l = "abc33dd";
        String[] split = l.split("\\.");
        System.out.println(Arrays.toString(split));
        Calculator calculator = new Calculator();

        int result = calculator.calculate(10, 5, (aa, bb) -> aa + bb);
        System.out.println("Result: " + result);
    }
}

interface MathOperation {
    int operate(int a, int b);
}

class Calculator {
    public int calculate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operate(a, b);
    }

}
