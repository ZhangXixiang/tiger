package com.zoo.tiger.me.test;

import java.util.LinkedList;

public class LinkedListTest {

    public static void main(String[] args) {
        ring(10, 5);
        System.out.println("========================");
        ring2(10, 5, 2);
    }

    // 约瑟夫环
    public static void ring(int n, int m) {
        LinkedList<Integer> q = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            q.add(i);
        }
        int k = 2;
        int element = 0;
        int i = 1;
        for (; i < k; i++) {
            element = q.poll();
            q.add(element);
        }
        i = 1;
        while (q.size() > 0) {
            element = q.poll();
            if (i < m) {
                q.add(element);
                i++;
            } else {
                i = 1;
                System.out.println(element);
            }
        }
    }


    // 约瑟夫环 一共n个人 从第k个人开始数第m个人出列 一直到所有人出列
    public static void ring2(int n, int m, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }
        int i = 1;
        int value = 0;
        for (; i < k; i++) {
            value = queue.poll();
            queue.add(value);
        }

        i = 1;
        while (!queue.isEmpty()) {
            value = queue.poll();
            if (i < m) {
                queue.add(value);
                i++;
            } else {
                i = 1;
                System.out.println(value);
            }
        }


    }


}
