package com.leetCode.twoSum;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lin.lvhua
 * @Date 2023/2/2 15:07
 * @Version 1.0
 * @Description
 */
public class twoSumDemo {
    public static void main(String[] args) {

    }

    public static int[] twoSum(int[] nums, int target) {
        int[] returnLists = new int[2];
        Date date = new Date();
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    returnLists[0] = i;
                    returnLists[1] = j;
                    return returnLists;
                }
            }
        }

        return null;
    }
}

