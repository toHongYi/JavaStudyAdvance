package com.leetCode.dp;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author lin.lvhua
 * @Date 2023/2/14 10:18
 * @Version 1.0
 * @Description :
 */
public class dp300 {
    public static void main(String[] args) {
        //
        int[] arr = new int[]{10,9,2,5,3,7,101,18,1111};
//        int[] arr = new int[]{1,9,2,3};
//        int[] arr = new int[]{1,2};
        int length = lengthOfLen02(arr);
        System.out.println("length = " + length);
    }


    public static int lengthOfLen02(int[] nums){
        int maxLen = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1); // 填充距离为1;

        for (int j = 0; j <nums.length; j++) {
            for (int i = 0; i < j; i++) {
                if (nums[i]<nums[j]){
                    dp[j] = Math.max(dp[j],dp[i]+1);
                }
            }
            maxLen = Math.max(maxLen,dp[j]);
        }

        return maxLen;
    }





    public static int lengthOfLIS2(int[] numl){
        if(numl.length == 0) {
            return 0;
        }
        int[] dp = new int[numl.length];
        int res = 0;
        Arrays.fill(dp, 1);
        for(int j = 0; j < numl.length; j++) {
            for(int i = 0; i < j; i++) {
                if(numl[i] < numl[j]) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            res = Math.max(res, dp[j]);
        }
        return res;
    }


    public static int lengthOfLIS(int[] nums) {
        // 设计算法,使得数据降低结构化;;
        // 双指针 + 数据存储;
        int left = 0, right = 0;
        // 最大长度
        int maxLen = 1;
        // 存储数据
        HashSet<Integer> setSave = new HashSet<>();
        for (int j = 1; j < nums.length; j++) {
            for (int i = 0; i < j; i++) {
                setSave.add(nums[left]);
                if (setSave.iterator().next() < nums[j]) {
                    setSave.add(nums[i]);
                } else {
                    setSave.clear();
                    left = i + 1;

                }
                // 如果最大长度
                if (maxLen < setSave.size()) {
                    maxLen = setSave.size();
                    setSave.clear(); // 清空
                }
            }
        }
        return maxLen;
    }

}





