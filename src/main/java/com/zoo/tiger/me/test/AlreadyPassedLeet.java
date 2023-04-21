package com.zoo.tiger.me.test;

import com.alibaba.fastjson2.JSON;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cglib.transform.FieldVisitorTee;
import org.springframework.util.StringUtils;

import java.util.*;

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
        /*System.out.println(isValid("({[]})"));
        longestCommonPrefix(new String[]{"flower", "f1low", "flight"});
        romanToInt("IV");

        System.out.println(isPalindrome1(1234321));
        System.out.println(addBinary("110", "10"));
        System.out.println(plusOne(new int[]{9}));
        System.out.println(Math.pow(1, 2));
        System.out.println(Math.pow(2, 3));

        int[] arr = new int[]{3, 2, 3, 1, 3, 1, 1, 2, 2, 2, 1, 3, 33, 4};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(JSON.toJSONString(arr));*/
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
        // reverseBetween(listNode1, 2, 3);

        ListNode listNode13 = new ListNode(1);
        ListNode listNode14 = new ListNode(2);
        ListNode listNode15 = new ListNode(4);
        listNode13.next = listNode14;
        listNode14.next = listNode15;
        listNode15.next = null;

        ListNode listNode23 = new ListNode(1);
        ListNode listNode24 = new ListNode(2);
        ListNode listNode25 = new ListNode(4);
        listNode23.next = listNode24;
        listNode24.next = listNode25;
        listNode25.next = null;

        mergeTwoLists(listNode13, listNode23);
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

    // 快排
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int index = getIndex(arr, low, high);
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }

    }

    public static int getIndex(int[] arr, int low, int high) {
        int num = arr[low];

        while (low < high) {

            // 从右边找比基准小的
            while (low < high && arr[high] >= num) {
                high--;
            }
            arr[low] = arr[high];

            // 从左边找比基准大的
            while (low < high && arr[low] <= num) {
                low++;
            }
            arr[high] = num;

        }
        return high;

    }

    // 58. 最后一个单词的长度
    public int lengthOfLastWord(String s) {
        String[] split = s.trim().split(" ");
        if (split.length == 0) {
            return s.length();
        } else {
            return split[split.length - 1].trim().length();
        }
    }

    // 58. 最后一个单词的长度
    public int lengthOfLastWord1(String s) {
        s = s.trim();
        int len = 0;
        for (int i = s.length() - 1; i > -1; i--) {
            if (s.charAt(i) != ' ') {
                len++;
            } else {
                break;
            }
        }
        return len;

    }

    // 66. 加一
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i > -1; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }

        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    // 67. 二进制求和
    public static String addBinary(String a, String b) {
        String res = "";
        int aLen = a.length();
        int bLen = b.length();
        int i = 0;
        int carrier = 0;
        while (i < Math.max(aLen, bLen)) {
            int aInt = aLen - 1 - i > -1 ? a.charAt(aLen - 1 - i) - '0' : 0;
            int bInt = bLen - 1 - i > -1 ? b.charAt(bLen - 1 - i) - '0' : 0;
            int realVal = aInt + bInt + carrier;
            if (realVal >= 2) {
                carrier = 1;
                res = realVal % 2 + res;
            } else {
                res = realVal + res;
                carrier = 0;
            }
            i++;
        }
        if (carrier > 0) {
            res = carrier + res;
        }

        return res;
    }

    // 9. 回文数
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String xStr = x + "";
        for (int i = 0; i < xStr.length() / 2; i++) {
            if (xStr.charAt(i) != xStr.charAt(xStr.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    // 9. 回文数 -- 不用字符串
    public static boolean isPalindrome1(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        // 反转一半的数字
        int temp = 0;
        while (x > temp) {
            temp = x % 10 + temp * 10;
            x /= 10;
        }
        return x == temp || x == temp / 10;
    }

    // 13. 罗马数字转整数
    public static int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            Integer integer = map.get(s.charAt(i));
            if ((i < s.length() - 1) && (integer < map.get(s.charAt(i + 1)))) {
                res -= integer;
            } else {
                res += integer;
            }
        }
        return res;
    }

    // 14. 最长公共前缀
    public static String longestCommonPrefix(String[] strs) {

        String compareStr = strs[0];
        int index = 0;
        for (int j = 0; j < compareStr.length(); j++) {
            index++;
            for (int i = 1; i < strs.length; i++) {
                if ((j >= strs[i].length()) || compareStr.charAt(j) != strs[i].charAt(j)) {
                    return compareStr.substring(0, index - 1);
                }
            }
        }
        return compareStr;
    }

    // 20. 有效的括号 [{()}]
    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char item : chars) {
            switch (item) {
                case '[':
                case '{':
                case '(':
                    stack.push(item);
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }

    // 21. 合并两个有序链表
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = null;
        ListNode tail = head;
        while (list1 != null || list2 != null) {
            Integer value1 = null != list1 ? list1.val : null;
            Integer value2 = null != list2 ? list2.val : null;
            ListNode listNode = null;
            if (value1 != null && (value2 == null || value2 > value1)) {
                listNode = new ListNode(value1);
                list1 = null != list1 ? list1.next : null;
            } else {
                listNode = new ListNode(value2);
                list2 = null != list2 ? list2.next : null;
            }
            if (head == null) {
                head = listNode;
                tail = head;
            } else {
                tail.next = listNode;
                tail = tail.next;
            }
        }
        return head;
    }

    // 21. 合并两个有序链表 --递归
    public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if (null == list1) {
            return list2;
        } else if (null == list2) {
            return list1;
        } else if (list1.val > list2.val) {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        } else {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
    }


    static class ListNode {

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

    // 15. 三数之和
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);
        int n = nums.length;
        for (int first = 0; first < nums.length; first++) {
            // 跳过相同的值
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            for (int second = first + 1; second < nums.length; second++) {
                if (second > 0 && nums[second] == nums[second - 1]) {
                    continue;
                }
                int third = n - 1;
                int target = -nums[first];

                while ((nums[third] + nums[second] > target) && second < third) {
                    third--;
                }
                if (nums[first] + nums[second] + nums[third] == 0) {
                    List<Integer> son = new ArrayList();
                    son.add(nums[first]);
                    son.add(nums[second]);
                    son.add(nums[third]);
                    res.add(son);
                }

            }
        }
        return res;
    }
}
