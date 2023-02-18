package com.hongyi.springbootkafka.controller;

/**
 * @author HongYi
 * @version 1.0
 * @date 2023/2/4 10:34
 * @description:
 */
public class Demo02 {
    public static void main(String[] args) {
        String str = "babad";
        String s = longestPalindrome(str);
        System.out.println("s = " + s);
    }

    public static String longestPalindrome(String s) {
        String max = "";
        int len = s.length();

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                // 取出来
                String split = s.substring(i, j+1);
                if (isPalindromic(split)){ // 如果是最大的,那么重置数据
                    max = split.length()>max.length()?split:max;
                }
            }
        }

        return max;
    }

    /**
     * 给定字段是否为回文数
     * @param split
     * @return
     */
    private static boolean isPalindromic(String split) {
        int len = split.length();
        // 字节一个一个的进行对比;
        for (int i = 0; i <len/2; i++) {
            if (split.charAt(i) != split.charAt(len-i-1)){
                return false;
            }

        }
        return true;
    }
}
