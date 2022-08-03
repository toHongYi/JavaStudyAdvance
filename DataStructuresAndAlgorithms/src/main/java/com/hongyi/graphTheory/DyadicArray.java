package com.hongyi.graphTheory;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/2 11:26
 * @description: 前置学习：二维数组
 */
public class DyadicArray {
    public static void main(String[] args) {

        //数组类型[] [] 数组名;    声明一个二维数组，建议使用这种声明
        int[][] num;
        // 或者 数组类型 数组名[] [];        //声明一个二维数组
        int num3[][];

        // 声明多维数组
        int num301[][][];
        int num302[][][];

        int[][] array1 = new int[10][10];
        int array2[][] = new int[10][10];

        int array3[][] = new int[][]{{1, 2, 3}, {2, 1, 3}, {4, 2, 5}};
        int array4[][] = {{1, 2, 3}, {2, 1, 3}, {4, 2, 5}};

        // 不定长二维数组
        int[][] array301 = new int[3][];
        // array301[0] = new int[]{1,3,4};
        array301[0] = new int[2];
        array301[1] = new int[1];
        array301[2] = new int[4];

        // 获取二维数组长度
        int length = array301.length;
        //
        int ints = array301[0].length;

        // 访问二维数组
        int array103[][] = new int[][]{{1, 2, 3}, {2, 1, 3, 4, 7}, {4, 2, 5}};
        System.out.println("array3:" + array103[1][4]);

        // 遍历二维数组
        for (int i = 0; i < array103.length; i++) {
            for (int j = 0; j < array103[i].length; j++) {
                System.out.println(array103[i][j]);
            }
        }

        // foreach遍历
        for (int[] ints1 : array103) {
            for (int i : ints1) {
                System.out.println("foreach = " + i);
            }
        }
    }





    public boolean containsDuplicate(int[] nums) {
        List<String> arrays = new ArrayList<String>();

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }

            }
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        return Arrays.stream(nums).distinct().count() != nums.length;
    }
}
