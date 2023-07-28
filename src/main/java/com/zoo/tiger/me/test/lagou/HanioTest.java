package com.zoo.tiger.me.test.lagou;


/**
 * 递归
 */
public class HanioTest {


    public static void main(String[] args) {
        hanio(3,"x","y","z");

    }


    public static void hanio(int n, String x, String y, String z) {
        if(n < 0) {
            System.out.println("error hanio level");
        } else if(n == 1) {
            System.out.println(x+"->"+z);
            return;
        } else {
            hanio(n-1,x,z,y);
            hanio(n-1,y,x,z);
            System.out.println(x+"->"+z);
        }
    }


}
