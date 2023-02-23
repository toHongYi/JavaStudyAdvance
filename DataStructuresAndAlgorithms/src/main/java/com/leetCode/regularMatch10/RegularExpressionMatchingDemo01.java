package com.leetCode.regularMatch10;

/**
 * @Author lin.lvhua
 * @Date 2023/2/22 14:46
 * @Version 1.0
 * @Description 开始做leetCode上的正则表达式匹配相关题
 * . 表示可匹配0个或多个
 */
public class RegularExpressionMatchingDemo01 {

    public static void main(String[] args) {
        String s = "aa", p = "a*";
//        String s = "aaaaabbbb",p = "aa*.*";
//        String s = "ab", p = ".*";
        boolean match = isMatch(s, p);
        System.out.println("正则表达式匹配结果match = " + match);

    }


    /**
     * 进行匹配
     * 关键之处在于:查验其匹配状态;
     * 存在一对多的情况，而非常有的一对一
     * 重点在于：如果解决一对多匹配、赋值问题
     */
    public static boolean isMatch(String s, String p) {
        char[] charArray = s.toCharArray();
        char[] toCharArray = p.toCharArray();

        // 二维数组,保存是否匹配的状态
        boolean[][] dp = new boolean[s.length()][p.length()];

        // 初始值,第一个对角
        if (charArray[0] == toCharArray[0] || toCharArray[0] == '.') {
            dp[0][0] = true;
        }

        // 运算,进行匹配
        for (int i = 1; i < s.length(); i++) {
            for (int j = 1; j < p.length(); j++) {
                // 对比测试;
                if (charArray[i] == toCharArray[j] || toCharArray[j] == '.') {
                    dp[i][j] = dp[i - 1][j - 1]; // 等于其前一组数据;
                } else if (toCharArray[j] == '*') { // 如果存在的是* 边进行拓展
                    if (charArray[j]==toCharArray[j-1]){ // 取*前一个数据,进行对比
                        dp[i][j] = dp[i][j-1] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[s.length()-1][p.length()-1];
    };


    /**
     * 进行匹配
     */
    public static boolean isMatchDP(String s, String p) {

        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();

        // dp[i][j]:表示s的前i个字符，p的前j个字符是否能够匹配
        boolean[][] dp = new boolean[cs.length + 1][cp.length + 1];

        // 初期值
        // s为空，p为空，能匹配上
        dp[0][0] = true;
        // p为空，s不为空，必为false(boolean数组默认值为false，无需处理)

        // s为空，p不为空，由于*可以匹配0个字符，所以有可能为true
        for (int j = 1; j <= cp.length; j++) {
            if (cp[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 填格子
        for (int i = 1; i <= cs.length; i++) {
            for (int j = 1; j <= cp.length; j++) {
                // 文本串和模式串末位字符能匹配上
                if (cs[i - 1] == cp[j - 1] || cp[j - 1] == '.') {  // 只对比 相同位置字串
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (cp[j - 1] == '*') { // 模式串末位是*
                    // 模式串*的前一个字符能够跟文本串的末位匹配上
                    if (cs[i - 1] == cp[j - 2] || cp[j - 2] == '.') {
                        dp[i][j] = dp[i][j - 2]      // *匹配0次的情况
                                || dp[i - 1][j];     // *匹配1次或多次的情况
                    } else { // 模式串*的前一个字符不能够跟文本串的末位匹配
                        dp[i][j] = dp[i][j - 2];     // *只能匹配0次
                    }
                }
            }
        }
        return dp[cs.length][cp.length];
    }

}
