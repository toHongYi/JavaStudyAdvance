package com.hongyi.graphTheory;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/2 11:33
 * @description: TODO
 */
public class newArray {
    public static void main(String[] args) {

        int[] arr1 = {1,2,3,4};
        int arr101[] = {1,2,3,4};

        int[] arr2 = new int[3];
        int arr201[] = new int[3];
        //数组名[索引]   对数组进行赋值
        arr2[0] = 3;
        arr2[1] = 898;
        arr2[2] = 32;

        int[] arr3 = new int[]{1,2,3,4};
        int arr301[] = new int[]{1,2,3,4};

        System.out.println("arr3[2] = " + arr3[2]);
        System.out.println("teh result"+Array.get(arr3,2));

        //数组名[索引]   对数组进行访问
        System.out.println("arr1.length = " + arr1.length);     //3
        System.out.println("arr1[0] = " + arr1[0]);


    }
}
