package com.zoo.tiger.me.test;



import java.util.List;
import java.util.ArrayList;


public class Main {

    public static void main(String[] param) {

        Tree t4 = new Tree(4, null, null);
        Tree t5 = new Tree(5, null, null);
        Tree t3 = new Tree(3, null, t4);
        Tree t2 = new Tree(2, null, t5);
        Tree t1 = new Tree(1, t2, t3);

        List<Integer> res = getRightView(t1);

        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    /**
     * 1            <---
     * /   \
     * 2     3         <---
     * \     \
     * 5     4       <---
     *
     * @param tree
     * @return
     */
    static List<Integer> res = new ArrayList<Integer>();

    public static List<Integer> getRightView(Tree tree) {
        if (tree == null || tree.val == -1) {
            return res;
        }

        res.add(tree.val);
        Tree right = tree.right;
        Tree left = tree.left;
        Tree cur = new Tree(-1, null, null);
        if (null != right) {
            cur = right;
        } else if (null != left) {
            cur = left;
        }
        getRightView(cur);

        return res;

    }

    static class Tree {

        int val;

        Tree left;

        Tree right;

        public Tree(int val, Tree left, Tree right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

}

