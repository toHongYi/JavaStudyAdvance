package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/23 9:52
 * @description: 希尔排序是希尔(Donald Shell)于1959年提出的一种排序算法。希尔排序也是一种插入排序,它是简单插入排序经过
 *      改进后的一个更高效的版本,也称为缩小增量排序。
 * BaseDesign:
 *      希尔排序是把记录按下标的一定增量分组,对每组使用直接插入排序算法排序;随着增量逐渐减少,每组包含的关键词越来越多,当增量减至1时，,
 *   整个文件恰被分成一组,算法便终止;
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
    }

    // 使用逐步推导编写希尔排序
    public static void shellSort(int[] arr) {
        int temp = 0;
        // 希尔排序的第一轮排序:
        // 因为第一轮排序,是将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组,每组2个元素),步长5
            for (int j = i - 5; j >= 0; j -= 5) {
                // 如果当前元素大于加上步长后的那个元素后,说明交换
                if (arr[j] > arr[j + 5]) {
//                    int temp = arr[j]; 将其放到外部,用于减少初始化次数;
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序第一轮之后:" + Arrays.toString(arr));

        // 希尔排序的第二轮排序:
        // 因为第二轮排序,是将10个数据分成了5/2组 = 2组
        for (int i = 2; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组,每组2个元素),步长5
            for (int j = i - 2; j >= 0; j -= 2) {
                // 如果当前元素大于加上步长后的那个元素后,说明交换
                if (arr[j] > arr[j + 2]) {
//                    int temp = arr[j]; 将其放到外部,用于减少初始化次数;
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("希尔排序第二轮之后:" + Arrays.toString(arr));


        // 希尔排序的第三轮排序:
        // 因为第三轮排序,是将10个数据分成了2/2组 = 1组
        for (int i = 1; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组,每组2个元素),步长5
            for (int j = i - 1; j >= 0; j -= 1) {
                // 如果当前元素大于加上步长后的那个元素后,说明交换
                if (arr[j] > arr[j + 1]) {
//                    int temp = arr[j]; 将其放到外部,用于减少初始化次数;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序第三轮之后:" + Arrays.toString(arr));

    }


}
