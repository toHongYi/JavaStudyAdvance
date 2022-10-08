package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/8 23:10
 * @description: 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 90, 123};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
//        selectSort(arr);
        selectSortEnhance(arr);


        // 测试一下冒泡排序的速度O(n^2),给8万个数据,测试
        // 测试要给80000个的随机数组
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr2[i] = (int) (Math.random() * 8000000); //生成一个[0,8000000]随机数
        }
        System.out.println(Arrays.toString(arr2));

        System.out.println("after sort");
        long before = System.currentTimeMillis();
        selectSortEnhance(arr2);
        System.out.println("time = " + (System.currentTimeMillis() - before));

        // 时间在3s左右,要比冒泡排序速度要快;虽然复杂度一致;
    }

    public static void selectSortEnhance(int[] arr) {
        // 因为已经发现了规律, 所有可以使用for循环来解决
        // 选择排序时间复杂度也是 O(n^2)
        for (int i = 0; i < arr.length; i++) {
            // 第一轮
            int minIndex = i;
            int min = arr[i];
            for (int j = 0 + 1; j < arr.length; j++) {
                if (min > arr[j]) { // 说明假定的最小值,并不是最小               【在这里进行修改 < ,即可变成从大到小排序】
                    min = arr[j]; // 重置min;
                    minIndex = j; // 重置minIndex;
                }
            }
            // 将最小值,放在arr[0],既交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.println("第" + (i + 1) + "轮后");
//            System.out.println(Arrays.toString(arr)); // [1, 34, 119, 101]
        }
    }

    // 选择排序
    public static void selectSort(int[] arr) {
        // 在推导的过程中,已经发现了规律,因此可以使用for来解决

        // 使用逐步推导的方式来,讲解选择排序
        // 第一轮
        // 原始数组:   101,34,119,1
        // 第一轮排序:  1,34,119,101
        // 算法 先简单--》做复杂,就是把一个复杂的算法,拆分成简单的问题—》逐步解决:

        // 第一轮
        int minIndex = 0;
        int min = arr[0];
        for (int j = 0 + 1; j < arr.length; j++) {
            if (min > arr[j]) { // 说明假定的最小值,并不是最小
                min = arr[j]; // 重置min;
                minIndex = j; // 重置minIndex;
            }
        }
        // 将最小值,放在arr[0],既交换
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
        }
        System.out.println("第1轮后");
        System.out.println(Arrays.toString(arr)); // [1, 34, 119, 101]


        // 第二轮
        minIndex = 1;
        min = arr[1];
        for (int j = 1 + 1; j < arr.length; j++) {
            if (min > arr[j]) { // 说明假定的最小值,并不是最小
                min = arr[j]; // 重置min;
                minIndex = j; // 重置minIndex;
            }
        }
        // 将最小值,放在arr[0],既交换
        if (minIndex != 1) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }

        System.out.println("第2轮后");
        System.out.println(Arrays.toString(arr)); //[1, 34, 119, 101]


        // 第3轮
        minIndex = 2;
        min = arr[2];
        for (int j = 2 + 1; j < arr.length; j++) {
            if (min > arr[j]) { // 说明假定的最小值,并不是最小
                min = arr[j]; // 重置min;
                minIndex = j; // 重置minIndex;
            }
        }
        // 将最小值,放在arr[0],既交换
        if (minIndex != 2) {
            arr[minIndex] = arr[2];
            arr[2] = min;
        }

        System.out.println("第3轮后");
        System.out.println(Arrays.toString(arr)); //[1, 34, 101, 119]
    }
}
