package com.zoo.tiger.me.test.lagou;

import com.zoo.tiger.me.test.AlreadyPassedLeet;
import org.apache.kafka.common.message.LeaderAndIsrRequestData;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 遍历的时间复杂度都是O(n)
 */
public class TreeTest {

    /**
     * 前序遍历
     *
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
     *
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
     *
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

    /**
     * 层序遍历
     */
    public static void cellOrder(AlreadyPassedLeet.TreeNode treeNode) {
        LinkedList<AlreadyPassedLeet.TreeNode> list = new LinkedList<>();
        list.add(treeNode);
        while (!list.isEmpty()) {
            AlreadyPassedLeet.TreeNode poll = list.poll();
            System.out.println(poll.val);
            if (poll.left != null) {
                list.add(poll.left);
            }
            if (poll.right != null) {
                list.add(poll.right);
            }
        }
    }

    /**
     * 蛇形遍历 这里需要优化 两个stack
     */
    public static void snakeOrder(AlreadyPassedLeet.TreeNode treeNode) {
        LinkedList<Integer> list = new LinkedList<>();
        Stack<AlreadyPassedLeet.TreeNode> stack1 = new Stack<>();
        Stack<AlreadyPassedLeet.TreeNode> stack2 = new Stack<>();
        list.add(treeNode.val);
        stack1.add(treeNode);

        while (stack1.isEmpty() || stack2.isEmpty()) {
            if (stack2.isEmpty() && stack1.isEmpty()) {
                break;
            }

            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    AlreadyPassedLeet.TreeNode pop = stack1.pop();
                    if (null != pop) {
                        stack2.push(pop.right);
                        stack2.push(pop.left);
                        list.add(pop.right.val);
                        list.add(pop.left.val);
                    }
                }
            }
            if (stack1.isEmpty()) {
                while (!stack2.isEmpty()) {
                    AlreadyPassedLeet.TreeNode pop = stack2.pop();
                    if (null != pop) {
                        stack1.push(pop.left);
                        stack1.push(pop.right);
                        list.add(pop.left.val);
                        list.add(pop.right.val);
                    }
                }
            }
        }

        // 基数层，比如第一层，头结点入队列，判断队列知否不为空，开始出队，入栈
        while (!list.isEmpty()) {
            AlreadyPassedLeet.TreeNode poll = list.poll();
            if (poll.left != null || poll.right != null) {
                System.out.println(poll.val);
                if (poll.left != null) {
                    stack.add(poll.left);
                }
                if (poll.right != null) {
                    stack.add(poll.right);
                }
                count++;
            }
        }

    }

    /**
     * 删除数组中的重复数据
     */
    public void removeDuplicatedNums(int[] nums) {
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[0];
            if (nums[i] != temp) {
                nums[len] = temp;
            }
            len++;
        }

        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }

    }

    /**
     * 数组中出现次数超过一半
     */
    public static void overHalf(int[] arr) {

        int times = 1;
        int temp = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] != temp) {
                times--;
            } else {
                times++;
            }

            // 重置对冲的值
            if (times == -1) {
                times = 1;
                temp = arr[i];
            }
            System.out.println(arr[i]);
        }

    }

    /**
     * 棋盘走法 到达第i行j列的走法总数
     */
    public static int getPath(int[][] matrix, int i, int j) {

        if (matrix[i][j] == -1) {
            return 0;
        }
        if (i > 0 && j > 0) {
            return getPath(matrix, i - 1, j) + getPath(matrix, i, j - 1);
        } else if (i == 0) {
            return getPath(matrix, i, j - 1);
        } else if (j == 0) {
            return getPath(matrix, i - 1, j);
        } else {
            return 1;
        }
    }

    /**
     * 前缀表达式
     * * 2 + 2 3 --> (2+3)*2
     */
    public static void main() {
        String str = "* 2 + 2 3";
        String[] s = str.split(" ");
        Stack<Integer> numStack = new Stack();
        for (int i = s.length; i >= 0; i--) {
            if (!s[i].equals("*") && !s[i].equals("+") && !s[i].equals("-") && !s[i].equals("/")) {
                numStack.push(Integer.parseInt(s[i]));
            } else {
                int res = cal(s[i], numStack.pop(), numStack.pop());
                numStack.push(res);
            }
        }
        System.out.println(numStack.pop());
    }

    public static int cal(String regx, int a, int b) {
        if (regx.equals("+")) {
            return a + b;
        } else if (regx.equals("-")) {
            return a - b;
        } else if (regx.equals("*")) {
            return a * b;
        } else {
            return a / b;
        }
    }


}
