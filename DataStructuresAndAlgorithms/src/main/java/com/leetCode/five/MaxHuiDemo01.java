package com.leetCode.five;

import java.util.HashMap;

/**
 * @Author lin.lvhua
 * @Date 2023/2/3 16:54
 * @Version 1.0
 * @Description
 */
public class MaxHuiDemo01 {
    public static void main(String[] args) {
        String str = "abab";
        String palindrome = longestPalindrome(str);
        System.out.println("palindrome = " + palindrome);
    }

    public static String longestPalindrome(String s) {

        String returnStr = "";
        int length = s.length();
        int left = 0,right = 0;

        for (int i = 0; i < s.length(); i++) {

            while (right < length) {

                int limitLen = left + (length - left) / 2 + 1; // 超过这里就不需要遍历了,直接退出
                String substring = s.substring(left, right);
                String[] doReverse = doReverse(substring);
                if (doReverse.equals(s.substring(limitLen, right))) {
                    //
                    if (right - left == Math.max(returnStr.length(), right - left)) {
                        returnStr = s.substring(left, right);
                    }
                }
                right++;
            }
        }
        return returnStr;
    }

    /**
     * 功能: 字符串反转
     *
     * @param result
     */
    private static String[] doReverse(String[] result) {
        if (result.length==1){
            return result;
        }
        int length = result.length;
        String[] str = new String[length];
        for (int i = length - 1, j = 0; i >= 0; i--, j++) {
            str[j] = result[i];
        }
        return str;
    }
    private static String[] doReverse(String result) {
        String[] split = result.split("");
        return doReverse(split);
    }

}
