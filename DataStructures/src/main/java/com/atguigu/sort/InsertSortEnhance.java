package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/9 23:47
 * @description:
 */
public class InsertSortEnhance {
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
        insertSort(arr2);
        System.out.println("time = " + (System.currentTimeMillis() - before));

        int[] arr = {101, 34, 119, 1, 89};
        insertSort(arr);
    }


    // 插入排序
    public static void insertSort(int[] arr) {
        // 第一轮 {101,34,119,1}; =》 {34,101,119,1}

        // {101,34,119,1}; =》 {101,101,34,119,1} || 辅助变量存储 arr[1]
        // 定义待插入的数
//        for (int i = 1; i < arr.length; i++) {
        for (int i = 1; i <= arr.length-1; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1; // 即arr[1]的前面的这个数的下标

            // 给indexVal 找到插入的位置
            // 说明
            // 1.insertIndex >0 保证 在给indexVal 找插入位置,不越界
            // 2. insertIndex < arr[insertIndex] 待插入的数,还没有找到插入位置
            // 3. 就需要将arr[insertIndex] 后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {  // 从大到小 insertVal > arr[insertIndex]
                arr[insertIndex + 1] = arr[insertIndex]; //arr[]insertIndex
                insertIndex--;
            }
            // 当退出while循环时,说明插入的位置找到,insertIndex + 1
            // 理解不了,可以debug;

//            arr[insertIndex+1] = insertVal;
            // 这里我们判断是否需要赋值,是对上方的一种优化策略;【若不需要赋值,那么insertIndex + 1 = 跟target下标一致】
            if (insertIndex + 1 != i) { // 当前
                arr[insertIndex + 1] = insertVal;
            }

//            System.out.println("第" + i + "轮插入"); // [34, 101, 119, 1]
//            System.out.println(Arrays.toString(arr));
        }
    }
}
