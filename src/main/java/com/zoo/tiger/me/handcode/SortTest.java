package com.zoo.tiger.me.handcode;

import com.alibaba.fastjson2.JSON;


public class SortTest {


    //    快排
    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 2, 4};
//        quickSort(arr, 0, 4);
        bubbleSort(arr);
        System.out.println(JSON.toJSONString(arr));

    }

    public static void bubbleSort(int[] arr) {
        for(int i = 0;i < arr.length;i++){
            for(int j = i; j < arr.length; j++) {
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

    }



    /**
     * 快排
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int index = getIndex1(arr, low, high);
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }
    }

    public static int getIndex(int[] arr, int low, int high) {
        // 选择基准
        int temp = arr[low];
        while (low < high) {
            // 从右边开始找 找比基准小的 进行交换
            while (low < high && arr[high] >= temp) {
                high--;
            }
            arr[low] = arr[high];
            // 从左边开始找 找比基准大的 进行交换
            while (low < high && arr[low] <= temp) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[high] = temp;
        return low;
    }


    public static int getIndex1(int[] arr, int low, int high) {
        int temp = arr[high];
        while (low < high) {
            // 从左边开始找 找比基准小的 进行交换
            while (temp >= arr[low] && low < high) {
                low++;
            }
            arr[high] = arr[low];

            while (temp <= arr[high] && low < high) {
                high--;
            }
            arr[low] = arr[high];
        }
        arr[low] = temp;
        return low;
    }


}
