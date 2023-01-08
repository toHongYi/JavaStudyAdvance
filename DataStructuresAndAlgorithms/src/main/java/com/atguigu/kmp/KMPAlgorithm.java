package com.atguigu.kmp;

import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2023/1/8 18:17
 * @description:
 */
public class KMPAlgorithm {
    public static void main(String[] args) {

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
//        String str2 = "BBC";

//        int[] next = kmpNext("AAAB"); // [0,1,2]
        int[] next = kmpNext("ABCDABD"); // [0,1,2]
        System.out.println("next = " + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index = " + index); // 15了

    }

    // 写出我们的kmp搜素算法

    /**
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表,是字串对应的部分匹配表
     * @return 如果是-1,就是没有匹配到;否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {

        // 遍历str1
        for (int i = 0, j = 0; i < str1.length(); i++) {

            // 需要处理 str1.charAt(i) != str2.charAt(j)
            // kmp算法核心点 可以验证
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) { // 找到了 j=3,i=
                return i - j + 1; // 上方对j++,但没对i++; 便+1
            }
        }
        return -1;
    }


    // 获取一个字符串(字串)的部分匹配值
    public static int[] kmpNext(String dest) {
        // 创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串是长度为1 部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当dest.charAt(i) != dest.charAt(j) , 我们需要从next[j-1]获取新的j
            // 直到我们发现有dest.charAt(i) == dest.charAt(j) 条件成立时才退出
            // 这是kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            // 当dest.charAt(i) == dest.charAt(j)条件满足时,部分匹配值就是要+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }


}
