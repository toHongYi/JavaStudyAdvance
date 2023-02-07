package com.leetCode;

/**
 * @Author lin.lvhua
 * @Date 2023/2/7 10:35
 * @Version 1.0
 * @Description
 */
public class SolutionDemo04 {
    public static void main(String[] args) {

//        String str = "cbbd";
//        String str = "babad";
        String str = "babad";
//        String str = "bb";

        String longestStr = theLongestStr(str);
        System.out.println("longestStr = " + longestStr);
    }


    /**
     * 试图推导出状态转移方程;
     * F(i,j) =
     *
     * @param s
     * @return
     */
    public static String theLongestStr(String s){
        // 如果为1,就是回文数了;
        if (s.length()<2){
            return s;
        }
        char[] chars = s.toCharArray();// 字节数组

        String returnStr = "";
        Integer begin = 1;
        int maxLen = 1; // 回文长度

        // 记录每个计算结果
        boolean[][] dp = new boolean[s.length()][s.length()];

        // 对角线都为true; 自己对照自己,对角线都是回文数;
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
        }

        for (int j = 1;j < s.length(); j++) {
            for (int i = 0; i < s.length(); i++) {

                if (chars[i] == chars[j] ){ // 判断是否中间部分; <=2 即为1位或2位; 中间部分,不考虑
                    if (j-i< 2){
                        dp[i][j] = true;
                    }else {
                        // 与中间对照;
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                if (dp[i][j] && maxLen < j - i + 1) {
                    begin = i;
                    maxLen = j - i + 1;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

}
