package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/11/7 22:50
 * @description: 对冒泡排序的一种改进。基本思想是：
 * 通过一趟排序将要排序的所有数据分割成独立的两部分,其中一部分的所有数据都比另一部分的
 * 所有数据要小,然后再按此方法对这两部分数据分别进行快速排序,整个排序过程可以递归进行,
 * 以此达到整个数据变成有序序列。
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
        int[] arr = {-9, 78, 0, 23, -567, 70,-1,900,4561};

        quickSort(arr, 0, arr.length - 1);
        System.out.println("arr=" + Arrays.toString(arr));

    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        // pivot 中轴
        int pivot = arr[(left + right) / 2];
        int temp = 0; // 临时变量,作为交换时使用
        // while循环的目的是让比pivot 值小的放到左边
        // 比pivot 值大的放到右边
        while (l < r) {
            // 在pivot的左边一直找,找到一个大于等于pivot值,才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在pvot的右边一直找,找到小于等于pivot值,才退出
            while (arr[r] > pivot) {
                r -= 1;
            }

            // 如果 l >= r 说明pivot的左右两边的值,已经按照左边全部是
            // 小于等于pivot的值,右边全部是大于等于pivot值
            if (l >= r) {
                break;
            }

            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完毕后,发现这个arr[l] == pivot值 相等 --, 前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 如果交换完毕后,发现这个arr[r] == pivot值 相等 ++, 后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        // 如果 l == r,必须l++, r--，否则为出现栈溢出
        // 保证是从中间开始
        if (l == r) {
            l += 1;
            r -= 1;
        }

        // 向左递归
        if (left < r){
            quickSort(arr,left,r);
        }

        // 向右递归
        if (right > r){
            quickSort(arr,l,right);
        }

    }

}
