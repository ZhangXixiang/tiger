package com.zoo.tiger.me.test;

import java.util.HashMap;

/**
 * @author Tiger
 */
public class AlreadyPassedLeet {

    // 两数之和 数组和target
    public int[] twoSum(int[] nums, int target) {
        // 构造一个map，key是值，value是数组的下标
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[2];
    }

    // 1 -> 2 -> 3  ++   3 -> 8 ->2  ==  4 -> 0 -> 6
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 遍历两个链表 如果两个链表没有遍历完 一直遍历 还需要一个进位 carrier
        int carrier = 0;
        ListNode head = null;
        ListNode tail = null;
        while (l1 != null || l2 != null || carrier != 0) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int realNum = (num1 + num2 + carrier) % 10;
            carrier = (num1 + num2 + carrier) / 10;
            // 构造链表
            if (null == head) {
                head = new ListNode(realNum);
                tail = head;
            } else {
                tail.next = new ListNode(realNum);
                tail = tail.next;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return head;
    }

    // 反转链表
    public static ListNode reverseList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode cur = head;
        ListNode pre = null;
        while (null != cur) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseList1(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode newHead = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;
        reverseBetween(listNode1, 2, 3);
    }

    // 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
    // dummyNode = new ListNode(-1);
    //      1 -> 2 -> 3 -> 4 -> 5 -> 6 left = 2 right = 3
    // d    p    l    r    s
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;

        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode leftNode = pre.next;
        ListNode rightNode = pre.next;
        for (int i = 0; i < right - left; i++) {
            rightNode = rightNode.next;
        }

        ListNode sucNode = rightNode.next;

        // 打断
        pre.next = null;
        rightNode.next = null;
        ListNode midNode = reverseList(leftNode);
        // pre.next = midNode; 这种也是可以的但是不要用 midNode.next = sucNode;这种写法
        // 关于链表的 打断再连接 需要再好好想想
        // 注意拼接的顺序
        pre.next = rightNode;
        leftNode.next = sucNode;
        return pre;


    }


}


class ListNode {

    int val;

    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
