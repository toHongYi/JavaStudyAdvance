package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/11/7 22:26
 * @description: 对交换式的希尔排序进行优化——》移位法
 */
public class ShellSortByInsert {

    public static void main(String[] args) {

        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        main(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void main(int[] args) {
        // 增量gap,并逐渐的缩小增量
        for (int gap = args.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素,逐个对其所在的组进行直接插入排序
            for (int i = gap; i < args.length; i++) {
                int j = i;
                int temp = args[j];
                if (args[j] < args[j - gap]) {
                    while (j - gap > 0 && temp < args[j - gap]) {
                        // 移动
                        args[j] = args[j - gap];
                        j -= gap;
                    }
                    // 当退出while后,就给temp
                    args[j] = temp;
                }
            }
        }
    }
}
