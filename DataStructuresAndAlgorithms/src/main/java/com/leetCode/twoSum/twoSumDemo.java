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
        int[] nums = new int[]{2,3,5,7,11,15};
        int targert = 9;
        int[] twoSum = twoSumDp(nums, targert);
        System.out.println("twoSum = " + twoSum[0]);
        System.out.println("twoSum = " + twoSum[1]);
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

    /** 试一试动态规划的撰写方式 */
    public static int[] twoSumDp(int[] nums, int target) {

        int len = nums.length;
        boolean[][] ints = new boolean[len][len];
        int[] returnTarget = new int[2];

        for (int j = 0; j < len ; j++) {
            for (int i = 0; i <j; i++) {
                if (nums[i] + nums[j] == target){
                    ints[i][j] = true;
                    returnTarget[0] = i;
                    returnTarget[1] = j;
                }
            }
        }

        return returnTarget;
    }

    /** 试一试hash冲突的撰写方式 */
    public static int[] twoSumCon(int[] nums, int target) {

        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}

