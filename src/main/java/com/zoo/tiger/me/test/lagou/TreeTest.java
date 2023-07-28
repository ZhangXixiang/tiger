package com.zoo.tiger.me.test.lagou;

import com.zoo.tiger.me.test.AlreadyPassedLeet;

/**
 * 遍历的时间复杂度都是O(n)
 */
public class TreeTest {

    /**
     * 前序遍历
     * @param treeNode
     */
    public static void preOrder(AlreadyPassedLeet.TreeNode treeNode) {
        if (null == treeNode) {
            return;
        }
        System.out.println(treeNode.val);
        inOrder(treeNode.left);
        inOrder(treeNode.right);
    }

    /**
     * 中序遍历
     * @param treeNode
     */
    public static void inOrder(AlreadyPassedLeet.TreeNode treeNode) {
        if (null == treeNode) {
            return;
        }
        inOrder(treeNode.left);
        System.out.println(treeNode.val);
        inOrder(treeNode.right);
    }

    /**
     * 后序遍历
     * @param treeNode
     */
    public static void postOrder(AlreadyPassedLeet.TreeNode treeNode) {
        if (null == treeNode) {
            return;
        }
        inOrder(treeNode.left);
        inOrder(treeNode.right);
        System.out.println(treeNode.val);
    }


}
