package com.zoo.tiger.me.test.lagou;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TreeEnhance {

    static class Tree {
        public int val;
        Tree lef;
        Tree right;

        public Tree(int val, Tree lef, Tree right) {
            this.val = val;
            this.lef = lef;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Tree tree2 = new Tree(2, null, null);
        Tree tree3 = new Tree(3, null, null);
        Tree tree1 = new Tree(1, tree2, tree3);

    }

    /**
     * 先序遍历
     *
     * @param root
     * @param res
     */
    public static void preOrder(Tree root, List<Integer> res) {
        res.add(root.val);
        if (null != root.lef) {
            preOrder(root.lef, res);
        }
        if (null != root.right) {
            preOrder(root.right, res);
        }
    }

    /**
     * 先序遍历--迭代
     * 根左右
     *
     * @param root
     */
    public static List<Integer> preOrder(Tree root) {
        List<Integer> res = new ArrayList<>();

        if (null == root) {
            return res;
        }
        Stack<Tree> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Tree pop = stack.pop();
            res.add(pop.val);
            if (null != pop.lef) {
                stack.push(pop.lef);
            }
            if (null != pop.right) {
                stack.push(pop.right);
            }
        }
        return res;
    }


    /**
     * 中序遍历
     * 左根右
     *
     * @param root
     * @param res
     */
    public static void inOrder(Tree root, List<Integer> res) {
        if (null != root.lef) {
            preOrder(root.lef, res);
        }
        res.add(root.val);
        if (null != root.right) {
            preOrder(root.right, res);
        }
    }

    /**
     * 中序遍历--迭代
     * 左根右
     *
     * @param root
     */
    public static List<Integer> inOrder(Tree root) {
        List<Integer> res = new ArrayList<>();

        if (null == root) {
            return res;
        }
        Stack<Tree> stack = new Stack<>();
        Tree cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.lef;
            }

            Tree pop = stack.pop();
            res.add(pop.val);
            cur = cur.right;

        }
        return res;
    }


    /**
     * 后序遍历
     * 左右根
     *
     * @param root
     * @param res
     */
    public static void postOrder(Tree root, List<Integer> res) {
        if (null != root.lef) {
            preOrder(root.lef, res);
        }
        if (null != root.right) {
            preOrder(root.right, res);
        }
        res.add(root.val);
    }

    /**
     * 后序遍历
     * 左右根
     *
     * @param root
     */
    public static List<Integer> postOrder(Tree root) {

        LinkedList<Integer> res = new LinkedList<>();
        Stack<Tree> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {

            Tree pop = stack.pop();
            // 从前往后插  root -> right,root -> left,right,root
            res.addFirst(pop.val);

            if (null != pop.lef) {
                stack.push(pop.lef);
            }
            if (null != pop.right) {
                stack.push(pop.right);
            }

        }
        return res;
    }

    /**
     * 层序遍历
     * 根 左右 一层层遍历
     *
     * @param root
     */
    public static List<List<Integer>> cellOrder(Tree root) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        cellOrder(root, res, 0);
        return res;
    }

    /**
     * 层序遍历
     * 递归处理的时候，一般没有返回值，而是把返回值放在入参中的一部分去处理
     *
     * @param root
     * @param res
     * @param level
     */
    public static void cellOrder(Tree root, List<List<Integer>> res, int level) {
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }

        res.get(level).add(root.val);
        if (null != root.lef) {
            cellOrder(root.lef, res, level + 1);
        }
        if (null != root.right) {
            cellOrder(root.right, res, level + 1);
        }
    }

    /**
     * 层序遍历迭代实现
     * **
     **/
    public static List<List<Integer>> cellOrderHandSimple(Tree root) {
        LinkedList<Tree> nodeQueue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            //            这里只要判断poll出来的是上一层的总数即可，不需要单独把每一层扣出来
            int size = nodeQueue.size();
            List<Integer> cellRes = new ArrayList<>();
            for (int num = 0; num < size; num++) {
                Tree poll = nodeQueue.poll();
                cellRes.add(poll.val);
                if (null != poll.lef) {
                    nodeQueue.push(poll.lef);
                }
                if (null != poll.right) {
                    nodeQueue.push(poll.right);
                }
            }
            res.add(cellRes);
        }
        return res;
    }


    /**
     * 层序遍历迭代实现
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> cellOrderHand(Tree root) {
        LinkedList<Tree> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> res = new ArrayList<>();
        List<Tree> treeNode = new ArrayList<>();
        while (!queue.isEmpty()) {
            int getNum = 0;
            List<Integer> sonRes = new ArrayList<>();
            while (queue.size() != getNum) {
                Tree poll = queue.poll();
                sonRes.add(poll.val);
                getNum++;
                if (null != poll.lef) {
                    treeNode.add(poll.lef);
                }
                if (null != poll.right) {
                    treeNode.add(poll.right);
                }
            }
            res.add(sonRes);

            for (int i = 0; i < treeNode.size(); i++) {
                queue.add(treeNode.get(i));
            }
        }
        return res;
    }


    public static List<LinkedList<Integer>> snakeOrder(Tree root) {
        List<LinkedList<Integer>> res = new LinkedList<>();
        snakeOrder(root, res, 0);
        return res;
    }

    /**
     * 蛇形递归
     *
     * @param root
     * @param res
     * @param level
     */
    public static void snakeOrder(Tree root, List<LinkedList<Integer>> res, int level) {
        if (null == root) {
            return;
        }

        if (res.size() == level) {
            res.add(new LinkedList<>());
        }

        LinkedList<Integer> integers = res.get(level);
        if (level % 2 == 0) {
            integers.addLast(root.val);
        } else {
            integers.addFirst(root.val);
        }

        if (null != root.lef) {
            snakeOrder(root.lef, res, level + 1);
        }
        if (null != root.right) {
            snakeOrder(root.right, res, level + 1);
        }

    }

    /**
     * 蛇形遍历
     *
     * @param root
     * @return
     */
    public static List<LinkedList<Integer>> snackOrderHandSimple(Tree root) {

        LinkedList<Tree> nodeQueue = new LinkedList<>();
        LinkedList<Integer> cellRes = new LinkedList<>();
        List<LinkedList<Integer>> res = new ArrayList<>();

        nodeQueue.add(root);
        // 结束标记
        nodeQueue.add(null);
        boolean order = true;
        while (!nodeQueue.isEmpty()) {
            Tree poll = nodeQueue.poll();
            if (null != poll) {
                if (order) {
                    cellRes.add(poll.val);
                } else {
                    cellRes.addFirst(poll.val);
                }
                if (null != poll.lef) {
                    nodeQueue.add(poll.lef);
                }
                if (null != poll.right) {
                    nodeQueue.add(poll.right);
                }
            } else {
                res.add(cellRes);
                cellRes = new LinkedList<>();
                if (!nodeQueue.isEmpty()) {
                    nodeQueue.add(null);
                }
                order = !order;
            }
        }
        return res;
    }


    /**
     * 蛇形层序遍历迭代实现 这里搞复杂了
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> snackOrderHand(Tree root) {
        LinkedList<Tree> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> res = new ArrayList<>();
        List<Tree> treeNode = new ArrayList<>();
        boolean leftOrder = false;
        while (!queue.isEmpty()) {
            int getNum = 0;
            List<Integer> sonRes = new ArrayList<>();
            while (queue.size() != getNum) {
                Tree poll = queue.poll();
                sonRes.add(poll.val);
                getNum++;
                if (leftOrder) {
                    if (null != poll.right) {
                        treeNode.add(poll.right);
                    }
                    if (null != poll.lef) {
                        treeNode.add(poll.lef);
                    }
                } else {
                    if (null != poll.lef) {
                        treeNode.add(poll.lef);
                    }
                    if (null != poll.right) {
                        treeNode.add(poll.right);
                    }
                }
            }
            res.add(sonRes);

            for (int i = 0; i < treeNode.size(); i++) {
                if (leftOrder) {
                    queue.add(treeNode.get(i));
                } else {
                    queue.addFirst(treeNode.get(i));
                }
            }
            leftOrder = !leftOrder;
        }
        return res;
    }


}
