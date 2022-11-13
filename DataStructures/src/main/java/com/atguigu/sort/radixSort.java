package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/11/12 23:31
 * @description: 基数排序: 属于"分配式排序"(distribution sort),又称"桶子法"(bucket sort),顾名思义,它是通过键值的各个位的值,
 * 将要排序的元素分配至某些"桶"中,达到排序的作用
 * 按位置进行比较;【先个位、再十位、再百位】
 */
public class radixSort {
    public static void main(String[] args) {

        int arr[] = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    public static void radixSort(int[] arr) {

        // 第一轮排序(针对每个元素的个位进行排序处理)

        // 定义一个二维数组表示十个桶,每个桶就是一个一维数组
        // 1、二维i数组包含10个一维数组
        // 2、为了防止在放入数的时候,数据溢出,则每个一维数组(桶),大小定为arr.length
        // 3、很明确,基数排序是使用空间换时间的经典算法;
        int[][] bucket = new int[10][arr.length];  //第二个参数,设定每个桶的大小【极端情况】

        // 为了记录每个桶中,实际存放了多少个数据,我们定义一个一维数组来记录各个桶每次放入的数据的个数
        // 可以这样理解,
        // 比如bucketElementCounts[0],记录的就是bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        //  第1轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素的个位的值
            int digitOffElement = arr[j]/1 % 10;
            // 放入到对应的桶中
            bucket[digitOffElement][bucketElementCounts[digitOffElement]] = arr[j];
            bucketElementCounts[digitOffElement]++;
        }
        // 按照这个桶对的顺序(一维数组的下标依次取出数据,放入原来数组)
        int index = 0;
        // 遍历每一桶,并将桶中数据,放入到原始数组
        for (int k = 0; k < bucketElementCounts.length; k++){
            //如果桶中,有数据,我们才放入到原数组
            if (bucketElementCounts[k] != 0 ){
                // 循环该桶既第k个桶(既第k个一维数组),放入
                for (int l =0;l<bucketElementCounts[k];l++){
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            // 第1轮处理后,需要将每个bucketElementCounts[k] = 0 !!!!!
            bucketElementCounts[k] = 0;

        }
        System.out.println("第一轮,第个位的排序处理 arr= " + Arrays.toString(arr));

        //=======================================================================
        // 第2轮(针对每个元素的十位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素的个位的值
            int digitOffElement = arr[j]/10 % 10;
            // 放入到对应的桶中
            bucket[digitOffElement][bucketElementCounts[digitOffElement]] = arr[j];
            bucketElementCounts[digitOffElement]++;
        }
        // 按照这个桶对的顺序(一维数组的下标依次取出数据,放入原来数组)
        index = 0;
        // 遍历每一桶,并将桶中数据,放入到原始数组
        for (int k = 0; k < bucketElementCounts.length; k++){
            //如果桶中,有数据,我们才放入到原数组
            if (bucketElementCounts[k] != 0 ){
                // 循环该桶既第k个桶(既第k个一维数组),放入
                for (int l =0;l<bucketElementCounts[k];l++){
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮,第个位的排序处理 arr= " + Arrays.toString(arr));

        //=======================================================================
        // 第3轮(针对每个元素的百位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素的个位的值
            int digitOffElement = arr[j]/100 % 10; //748 /100 =>7【取整,再取余】
            // 放入到对应的桶中
            bucket[digitOffElement][bucketElementCounts[digitOffElement]] = arr[j];
            bucketElementCounts[digitOffElement]++;
        }
        // 按照这个桶对的顺序(一维数组的下标依次取出数据,放入原来数组)
        index = 0;
        // 遍历每一桶,并将桶中数据,放入到原始数组
        for (int k = 0; k < bucketElementCounts.length; k++){
            //如果桶中,有数据,我们才放入到原数组
            if (bucketElementCounts[k] != 0 ){
                // 循环该桶既第k个桶(既第k个一维数组),放入
                for (int l =0;l<bucketElementCounts[k];l++){
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
        }
        System.out.println("第一轮,第个位的排序处理 arr= " + Arrays.toString(arr));

    }
}
