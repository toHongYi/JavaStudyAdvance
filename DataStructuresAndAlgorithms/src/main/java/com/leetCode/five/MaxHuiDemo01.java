package com.leetCode.five;

import java.util.Arrays;
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
//        String byteToString = byteToString(str);

    }

    public static String longestPalindrome(String s) {

        String returnStr = "";
        int length = s.length();
        int left = 0,right =1;

        for (int i = 0; i < s.length(); i++) {

            while (right < length) {

                int limitLen = left + (length - left) / 2 + 1; // 超过这里就不需要遍历了,直接退出
                // 设置指针;中间部分;
                int middlePoint = left + (right - left) / 2 + 1;

                String substring = s.substring(left, right);
                String doReverse = doReverse(substring);
                String substring1 = s.substring(middlePoint-1, right+1);
                if (doReverse.equals(substring1)) {
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
    private static String doReverse(String result) {
        String[] split = result.split("");
        String[] strings = doReverse(split);
        return byteToString(strings);
    }

    private static String byteToString(String[] split) {
        StringBuffer buffer = new StringBuffer();
        for (String str:split){
            buffer.append(str);
        }
        String v = buffer.toString();
        return v;
    }

}
