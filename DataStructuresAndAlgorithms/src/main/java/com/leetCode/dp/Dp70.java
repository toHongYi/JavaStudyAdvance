package com.leetCode.dp;

/**
 * @Author lin.lvhua
 * @Date 2023/2/14 15:39
 * @Version 1.0
 * @Description
 */
public class Dp70 {

    public static void main(String[] args) {

        int climbStairs = climbStairs2(32);
        System.out.println("climbStairs = " + climbStairs);

    }

    // 计算次数
    public static int climbStairs2(int n){
        int[] arr = new int[n];
        arr[0] = 1;

        if ( n >= 2 ){
            arr[1] = 2;
        }

        // 接下来所有的上台阶数量都是这两级台阶获取到的;
        // 这是获取到斐波那契的数列组合而不是
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i-1]+arr[i-2];
        }

        return arr[n-1];
    }






    public static int climbStairs(int n) {
        int memo[] = new int[n + 1];
        return climbStairsMemo(n, memo);
    }

    public static int climbStairsMemo(int n, int[] memo) {

        if (memo[n] > 0) {
            return memo[n];
        }

        if (n == 1) {
            memo[n] = 1;
        } else if (n == 2) {
            memo[n] = 2;
        } else {
            memo[n] = climbStairsMemo(n - 1, memo) + climbStairsMemo(n - 2, memo);
        }
        return memo[n];
    }

}
