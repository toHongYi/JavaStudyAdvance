package com.atguigu.search;

import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/11/13 16:40
 * @description: 插值查找算法也要求数组是有序的;
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int arr[] = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        // 一到100的

        int index = insertValueSearch(arr, 0, arr.length - 1, 37);
        System.out.println("index = " + index);
//        System.out.println(Arrays.toString(arr));

        // 如果给定数据连续性不强;其效率未必优于二分查找
        int arr2[] = {1,8,10,89,1000,1000,1234};
        int i = insertValueSearch(arr2, 0, arr2.length - 1, 89);
        System.out.println("i = " + i);
    }
    // 编写插值查找算法

    /**
     * @param arr     : 传入数组
     * @param left    : left左边索引
     * @param right   : right右边索引
     * @param findVal : findVal 查找值
     * @return 如果找到, 就返回对应的下标
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        System.out.println("find Count~~");
        // 注意:finVal < arr[0] 和 finalVal > arr[arr.length - 1] 必须需要,
        // 否则我们得到的这个mid的值 可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];

        if (findVal > midValue) { // 说明应该向右边进行查找递归,
            return insertValueSearch(arr, left + 1, right, findVal);
        } else if (findVal < midValue) { //说明向左递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }
}
