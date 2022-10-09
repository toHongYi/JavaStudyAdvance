package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/9 23:16
 * @description: 插入排序L: 插入排序属于内部排序法,是对于欲排序的元素以插入的方式寻找该元素的适当位置,以达到排序的目的;
 * 排序思想:
 *  把n个待排序的元素看成是一个有序表和一个无序表;
 *
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        insertSort(arr);
    }


    // 插入排序
    public static void insertSort(int[] arr) {
        // 使用逐步推导的方式来讲解,便于理解
        // 第一轮 {101,34,119,1}; =》 {34,101,119,1}

        // {101,34,119,1}; =》 {101,101,34,119,1} || 辅助变量存储 arr[1]
        // 定义待插入的数
        int insertVal = arr[1];
        int insertIndex = 1 - 1; // 即arr[1]的前面的这个数的下标

        // 给indexVal 找到插入的位置
        // 说明
        // 1.insertIndex >0 保证 在给indexVal 找插入位置,不越界
        // 2. insertIndex < arr[insertIndex] 待插入的数,还没有找到插入位置
        // 3. 就需要将arr[insertIndex] 后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; //arr[]insertIndex
            insertIndex--;
        }
        // 当退出while循环时,说明插入的位置找到,insertIndex + 1
        // 理解不了,可以debug;
        arr[insertIndex + 1] = insertVal;

        System.out.println("第1轮插入"); // [34, 101, 119, 1]
        System.out.println(Arrays.toString(arr));

        // 第二轮
        insertVal = arr[2];
        insertIndex = 2 - 1; // 即arr[1]的前面的这个数的下标
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; //arr[]insertIndex
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第2轮插入");  // [34, 101, 119, 1]
        System.out.println(Arrays.toString(arr));

        // 第三轮
        insertVal = arr[3];
        insertIndex = 3 - 1; // 即arr[1]的前面的这个数的下标
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; //arr[]insertIndex
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第3轮插入");  // [34, 101, 119, 1]
        System.out.println(Arrays.toString(arr)); //[1, 34, 101, 119]


    }
}
