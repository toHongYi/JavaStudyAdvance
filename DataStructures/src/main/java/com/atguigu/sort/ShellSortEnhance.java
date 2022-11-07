package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/23 11:29
 * @description: 对有序序列在插入时采用交换法, 并测试排序速度
 *      移动式的冒泡排序;  【交换式】: 存在效率的不足;  优化为移位法
 */
public class ShellSortEnhance {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        // 测试要给80000个的随机数组
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr2[i] = (int) (Math.random() * 8000000); //生成一个[0,8000000]随机数
        }

        System.out.println("after sort");
        long before = System.currentTimeMillis();

        shellSort(arr2); //3347

        System.out.println("time = " + (System.currentTimeMillis() - before));


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
