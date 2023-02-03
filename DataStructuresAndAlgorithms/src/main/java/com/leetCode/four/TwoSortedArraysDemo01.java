package com.leetCode.four;

import java.util.Arrays;

/**
 * @Author lin.lvhua
 * @Date 2023/2/3 14:49
 * @Version 1.0
 * @Description
 */
public class TwoSortedArraysDemo01 {
    public static void main(String[] args) {

        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{3,4};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println("medianSortedArrays = " + medianSortedArrays);

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // 创建一个数组存储中间值数据;
        int[] allArrays = new int[m+n];
        int j = 0;
        for (int i = 0; i < nums1.length; i++,j++) {
            allArrays[j] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++,j++) {
            allArrays[j] = nums2[i];
        }

        int[] ints = Arrays.stream(allArrays).sorted().toArray();
        // 看看这里是不是已经贴合了,变成有序的了;

        // 找到中位数;
        // 两种情况,
        int length = ints.length;
        int middle = 0;
        double value = 0L;

        if (length % 2 == 0) { // 偶数判断
            middle = length/2;
            value = (ints[middle-1]+ ints[middle]);
            value = value/2 ;
        } else { //奇数
            middle = length/2;
            value = ints[middle];
        }

        return value;
    }

}
