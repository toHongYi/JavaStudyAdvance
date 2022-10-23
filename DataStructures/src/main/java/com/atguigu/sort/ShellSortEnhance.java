package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/23 11:29
 * @description: 对有序序列在插入时采用交换法, 并测试排序速度
 */
public class ShellSortEnhance {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
    }

    // 根据前面的逐步分析,使用循环处理
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0; //计数器
        // 希尔排序的第1轮排序
        // 因为第一轮排序,是将10个数据分成了5组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素(共gap组,每组个元素),步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素后,说明交换
                    if (arr[j] > arr[j + gap]) {
//                    int temp = arr[j]; 将其放到外部,用于减少初始化次数;
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + ++count + "轮之后:" + Arrays.toString(arr));
        }


    }

}
