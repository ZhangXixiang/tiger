package com.zoo.tiger.me.test;

import com.alibaba.fastjson2.JSON;

import java.util.*;

/**
 * @author Tiger
 */
public class AlreadyPassedLeet {


    //








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

        /**
         *      1
         *     2  2
         *    /\  /\
         *     3   3
         *
         *
         */

        TreeNode t6 = new TreeNode(3, null, null);
        TreeNode t7 = new TreeNode(3, null, null);
        TreeNode t1 = new TreeNode(2, null, t6);
        TreeNode t2 = new TreeNode(2, null, t7);
        TreeNode t3 = new TreeNode(1, t2, t1);
        System.out.println(isSymmetric(t3));


        generate(4);
        moveZeroes2(new int[]{3, 2, 0, 0, 1});
        String res = mergeAlternately("aa", "pqrs");
        System.out.println(res);
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(JSON.toJSONString(lists));
        /*System.out.println(isValid("({[]})"));
        longestCommonPrefix(new String[]{"flower", "f1low", "flight"});
        romanToInt("IV");

        System.out.println(isPalindrome1(1234321));
        System.out.println(addBinary("110", "10"));
        System.out.println(plusOne(new int[]{9}));
        System.out.println(Math.pow(1, 2));
        System.out.println(Math.pow(2, 3));*/

        int[] arr = new int[]{3, 26, 3, 140, 3, 1, 12, 2, 2, 21, 11, 3, 33, 4};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快排："+JSON.toJSONString(arr));
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
            arr[high] = arr[low];

        }
        arr[low] = num;
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
        for (int first = 0; first < n; ++first) {

            // 跳过相同的值
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = n - 1;
            int target = -nums[first];

            for (int second = first + 1; second < n; ++second) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                while ((nums[third] + nums[second] > target) && second < third) {
                    --third;
                }
                if (second == third) {
                    break;
                }
                if (nums[third] + nums[second] == target) {
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

    // 283. 移动零
    public static void moveZeroes1(int[] nums) {
        int length = nums.length;
        int count = 0;
        for (int i = 0; i < length; i++) {
            while (nums[i] == 0) {
                int index = i;
                // 判断是否已经不需要移动
                if (length - 1 - count > i) {
                    // 其他位向前移动
                    while (index <= length - 2) {
                        nums[index] = nums[index + 1];
                        index++;
                    }
                    nums[length - 1 - count] = 0;
                    count++;
                } else {
                    break;
                }
            }
        }
        System.out.println(nums);
    }

    // left right
    //    3,        2,0,0,1
    public static void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void moveZeroes2(int[] nums) {
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                if (left != right) {
                    swap(nums, left, right);
                }
                left++;
            }
            right++;
        }

    }

    public static void swap1(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    // 234. 回文链表
    public boolean isPalindrome(ListNode head) {
        if (null == head || null == head.next) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        for (int i = 0; i < sb.length() / 2; i++) {
            if (sb.charAt(i) != sb.charAt(sb.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome1(ListNode head) {

        List<Integer> val = new ArrayList<>();
        ListNode dummyNode = head;
        while (dummyNode != null) {
            val.add(dummyNode.val);
            dummyNode = dummyNode.next;
        }
        // 双指针
        int left = 0;
        int right = val.size() - 1;
        while (left < right) {
            if (!val.get(left).equals(val.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 142. 环形链表 II
    public ListNode detectCycle(ListNode head) {
        // 快慢指针
        // f = 2s
        // f = s + nb
        // s = nb 慢指针走了nb到了位置B，到交点需要走a+nb,slow再走a步

        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            if (null == fast || null == fast.next.next || null == fast.next) return null;
            fast = fast.next.next;
            slow = slow.next;
            // 相遇了
            if (fast == slow) {
                break;
            }
        }

        // 再走a步
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    // 104. 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        } else {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;
        }
    }

    // 当前层的所有节点
    public int maxDepth2(TreeNode root) {
        // 把每一层的放进Queue
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int levelNum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode poll = queue.poll();
                if (null != poll.left) {
                    queue.offer(poll.left);
                }
                if (null != poll.right) {
                    queue.offer(poll.right);
                }
                size--;
            }
            levelNum++;
        }
        return levelNum;
    }

    public int maxDepth1(TreeNode root) {
        int num = 0;
        getChild(root, num);
        return num;
    }

    // 感觉需要用递归
    public TreeNode getChild(TreeNode tree, int num) {
        if (tree == null) {
            return null;
        }

        if (tree.left != null) {
            num++;
            return getChild(tree, num);
        }
        if (tree.right != null) {
            num++;
            return getChild(tree.right, num);
        }
        return null;
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;


        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode() {
        }
    }

    // 121. 买卖股票的最佳时机 顺序 最大差 前面的小 后面的大  4 3 5 8 1 2
    public int maxProfit1(int[] prices) {
        int max = 0;
        for (int j = prices.length - 1; j >= 0; j--) {
            for (int i = j - 1; i >= 0; i--) {
                max = Math.max(prices[j] - prices[i], max);
            }
        }
        return max;
    }


    // 121. 买卖股票的最佳时机 顺序 最大差 前面的小 后面的大  4 3 5 8 1 2
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (min > prices[i]) {
                min = prices[i];
            } else {
                maxProfit = prices[i] - min;
            }
        }
        return maxProfit;
    }


    //    83. 删除排序链表中的重复元素
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode cur = head;
        while (null != cur.next) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    //    1768. 交替合并字符串
    public static String mergeAlternately(String word1, String word2) {

        int min = Math.min(word1.length(), word2.length());
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < min; i++) {
            res.append(word1.charAt(i)).append(word2.charAt(i));
        }
        String leftStr = word1.length() > word2.length() ? word1.substring(min, word1.length()) : word2.substring(min, word2.length());
        res.append(leftStr);
        return res.toString();
    }

    // 118. 杨辉三角
    /*public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        return ret;
    }*/

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || i == j) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j) + ret.get(i - 1).get(j - 1));
                }
            }
            ret.add(row);
        }
        return ret;

    }

    // 226. 翻转二叉树
    public TreeNode invertTree1(TreeNode root) {
        if (null == root) {
            return root;
        }
        TreeNode rigth = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = rigth;
        root.right = left;
        return root;
    }


    // 226. 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (null == root) {
            return root;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            TreeNode left = poll.left;
            TreeNode right = poll.right;

            poll.right = left;
            poll.left = right;
            if (null != right) {
                queue.offer(right);
            }
            if (null != left) {
                queue.offer(left);
            }
        }
        return root;
    }

    static StringBuilder ret = new StringBuilder();

    public static void inOrder(TreeNode root) {
        if (null != root.left) {
            inOrder(root.left);
        } else {
            ret.append("-");
        }
        ret.append(root.val);
        if (null != root.right) {
            inOrder(root.right);
        } else {
            ret.append("-");
        }
    }

    public static boolean isSymmetric1(TreeNode root) {
        inOrder(root);
        for (int i = 0; i < ret.length(); i++) {
            if (ret.charAt(i) != ret.charAt(ret.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSymmetric(TreeNode root) {
        return check(root,root);
    }

    public static boolean check(TreeNode t, TreeNode k) {
        LinkedList<TreeNode> queue = new LinkedList();
        queue.offer(t);
        queue.offer(k);

        while (!queue.isEmpty()) {
            TreeNode a = queue.poll();
            TreeNode b = queue.poll();

            if ((a == null && b == null)) {
                continue;
            } else if (a == null || b == null || a.val != b.val) {
                return false;
                // a.val == b.val
            } else {
                queue.offer(a.left);
                queue.offer(b.right);
                queue.offer(a.right);
                queue.offer(b.left);
            }
        }
        return true;
    }


}
