package com.zoo.tiger.me.test.sort;

import com.alibaba.fastjson2.JSON;


/**
 * @author Tiger
 */
public class SortTest {

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 4};
        heapSort(arr);
        System.out.println(JSON.toJSONString(arr));

    }

    // 选择排序
    public static void sortPick(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 最小数下标
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    min = j;
                }
            }
            // 获取到最小的之后 进行交换
            swap(arr, i, min);
        }
    }

    // 插入排序
    public static void sortInsert(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 2 1 4 5 从第二个开始
            int temp = arr[i];
            while (i > 0 && arr[i - 1] > arr[i]) {
                arr[i] = arr[i - 1];
                i--;
            }
            arr[i] = temp;
        }
    }

    // 冒泡排序
    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // 获取到最小的之后 进行交换
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    // 快排 2 1 4 5
    public static void sortQuick(int[] arr, int low, int high) {
        if(low < high) {
            int num = getNum(arr, low, high);
            sortQuick(arr, low, num - 1);
            sortQuick(arr, num + 1, high);
        }
    }

    public static int getNum(int[] arr, int low, int high) {
        int temp = arr[low];
        while(low < high) {
            // 从右边找小
            while (low < high && arr[high] >= temp) {
                high --;
            }
            arr[low] = arr[high];
            // 从左边找大
            while (low < high && arr[low] <= temp) {
                low ++;
            }
            arr[high] = arr[low];
        }
        arr[high] = temp;
        return low;
    }

    // 堆排序
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // 构建大顶堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 从堆顶开始取出元素，并进行排序
        for (int i = n - 1; i > 0; i--) {
            // 将堆顶元素（最大值）与当前未排序部分的末尾元素交换
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 重新调整堆结构
            heapify(arr, i, 0);
        }
    }

    // 将以root为根的子树调整为大顶堆
    public static void heapify(int[] arr, int n, int root) {
        int largest = root; // 初始化根节点为最大值
        int left = 2 * root + 1; // 左子节点索引
        int right = 2 * root + 2; // 右子节点索引

        // 如果左子节点大于根节点，更新最大值索引
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // 如果右子节点大于当前最大值，更新最大值索引
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // 如果最大值不是根节点，交换根节点与最大值节点
        if (largest != root) {
            int temp = arr[root];
            arr[root] = arr[largest];
            arr[largest] = temp;

            // 继续调整被交换的子树
            heapify(arr, n, largest);
        }
    }



    // 快排 2 1 4 5 nlogn
    public static void quickSort(int[] nums, int low, int high){
        if(low < high) {
            int index = getIndex(nums, low, high);
            quickSort(nums, low , index - 1);
            quickSort(nums, index + 1, high);
        }
    }

    public static int getIndex(int[] nums, int low, int high) {
        // 参考
        int temp = nums[low];

        while(low < high) {
            // 从右边找小的
            while(low < high && nums[high] >= temp) {
                high --;
            }
            nums[low] = nums[high];

            // 从左边找大的
            while(low < high && nums[low] <= temp) {
                low ++;
            }
            nums[high] = nums[low];
        }
        nums[low] = temp;

        return low;

    }


    /**
     * 交换arr的 i j 位置的数据
     * 
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
