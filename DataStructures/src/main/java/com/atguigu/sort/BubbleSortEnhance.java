package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/8 22:37
 * @description: 优化原有逻辑
 */
public class BubbleSortEnhance {
    public static void main(String[] args) {


        // 测试一下冒泡排序的速度O(n^2),给8万个数据,测试
        // 测试要给80000个的随机数组
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr2[i] = (int) (Math.random() * 8000000); //生成一个[0,8000000]随机数
        }
        System.out.println(Arrays.toString(arr2));

        System.out.println("after sort");
        long before = System.currentTimeMillis();
        bubbleSort(arr2);
        System.out.println("time = "+(System.currentTimeMillis()-before));


        // 优化二:如果我们在某趟排序中没有发现
        int[] arr = new int[]{3, 9, -1, 10, -2, 20};
        bubbleSort(arr);

    }

    // 方法封装
    private static void bubbleSort(int[] arr) {
        // 第一趟排序,就是将最大的数排在最后O(n^2)
        int temp = 0; // 临时变量
        boolean flag = false; // 标识变量,表示是否进行过交换;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面大的话,则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("第" + (i + 1) + "趟排序后的数组");
//            System.out.println(Arrays.toString(arr));
            if (flag == false) {  // 在一次排序中,一次交换都没有发生
                break;
            } else {
                flag = false; //重置flag,进行下次判断
            }
        }
    }
}
