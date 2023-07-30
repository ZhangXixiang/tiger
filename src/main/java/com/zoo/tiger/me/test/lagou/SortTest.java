package com.zoo.tiger.me.test.lagou;

import java.util.*;

public class SortTest {

    public static void main(String[] args) {
        int[] arr = {3, 3, 4, 4, 5, 2, 1, 140};
        //        quickSort(arr,0, arr.length - 1);
        //        quickSort1(arr, 0, arr.length - 1);
        //        insertSort(arr);
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));


        int[][] matrix = {
                {0, 1, 2, 0, 0, 0, 0},
                {0, 0, 0, 3, 4, 0, 0},
                {0, 0, 0, 0, 0, 5, 0},
                {0, 0, 0, 0, 0, 0, 8},
                {0, 0, 0, 0, 0, 0, 5},
                {0, 0, 0, 0, 0, 0, 6}};
        System.out.println("第一行="+Arrays.toString(matrix[0]));
        System.out.println("第一行="+Arrays.toString(matrix[0]));
        System.out.println(minPath(matrix));
        // {1,2,3,4,5,6} target=10
        int arr1[] = {1,2,3,4,5,6};
        System.out.println(Arrays.toString(twoSum(arr1, 10)));

        // 0,1,1,2,3,5
        System.out.println(fun(5));

        System.out.println("---------");
        insert(3);
        insert(4);
        insert(2);
        System.out.println(maxHeap.peek());

    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            int index = getIndex(arr, low, high);
            quickSort(arr, 0, index - 1);
            quickSort(arr, index + 1, high);
        }

    }

    public static int getIndex(int[] arr, int low, int high) {
        int num = arr[low];

        while (low < high) {
            // 从右边找小于目标值的
            while (low < high && arr[high] >= num) {
                high--;
            }
            arr[low] = arr[high];
            // 从左边找大于目标的
            while (low < high && arr[low] <= num) {
                low++;
            }
            arr[high] = arr[low];
        }

        arr[low] = num;
        return low;
    }


    /**
     * 获取比较的基准index，供下一次递归调用时使用
     *
     * @param arr
     * @param low
     * @param high
     */
    public static int getIndex1(int[] arr, int low, int high) {
        //        使用low作为基准
        int num = arr[low];
        while (low < high) {
            //            右边找比基准小
            while (low < high && arr[high] >= num) {
                high--;
            }
            arr[low] = arr[high];

            while (low < high && arr[low] <= num) {
                low++;
            }
            arr[high] = arr[low];
        }
        System.out.println("low" + low);
        System.out.println("high" + high);

        arr[high] = arr[low] = num;
        return high;
    }

    public static void quickSort1(int[] arr, int low, int high) {
        if (low < high) {
            int index = getIndex1(arr, low, high);
            quickSort1(arr, low, index - 1);
            quickSort1(arr, index + 1, arr.length - 1);
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length - 1; i++) {
            //取出的数就是基准
            int temp = arr[i];

            while (i > 0 && arr[i - 1] > temp) {
                arr[i] = arr[i - 1];
                i--;
            }
            arr[i] = temp;
        }
    }

    /**
     * 冒泡算法
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // 出现需要冒泡的情况 前面的数比后面的数大
                if (arr[i] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    /**
     * 最短路径和
     *
     * @param matrix
     * @return
     */
    public static int minPath(int[][] matrix) {
        return process(matrix, matrix[0].length - 1);
    }

    public static int process(int[][] matrix, int i) {
        // 到达A时
        if (i == 0) {
            return 0;
        } else {
            int distance = 999;
            for (int j = 0; j < i; j++) {
                if (matrix[j][i] != 0) {
                    int temp = matrix[j][i] + process(matrix, j);
                    if (temp < distance) {
                        distance = temp;
                    }
                }
            }
            return distance;
        }
    }
    // {1,2,3,4,5,6} target=10
    public static int[] twoSum(int[] arr, int target){
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0 ; i < arr.length ; i ++){
            if(!map.containsKey(target-arr[i])){
                map.put(arr[i],i);
            } else {
                return new int[]{i, map.get(target-arr[i])};
            }
        }
        return new int[]{};

    }

    // 斐波那契
    public static int fun(int n){
        if(n == 1) { return 0;}
        else if(n == 2) { return 1;}
        else {return fun(n-1) +fun(n-2);}
    }

    // 查找数据流中的中位数 大顶堆 小顶堆
    static int count = 0;
    static PriorityQueue minHeap = new PriorityQueue<>();
    static PriorityQueue maxHeap = new PriorityQueue(Comparator.reverseOrder());

    public static void insert(Integer num){
        // count从0开始 一个数的时候放大顶堆
         if(count % 2 == 0){
             minHeap.offer(num);
             maxHeap.offer(minHeap.poll());
         } else {
             maxHeap.offer(num);
             minHeap.offer(maxHeap.poll());
         }
         count++;
    }







}
