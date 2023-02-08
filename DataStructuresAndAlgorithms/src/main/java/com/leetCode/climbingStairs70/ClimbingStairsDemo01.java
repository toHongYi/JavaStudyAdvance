package com.leetCode.climbingStairs70;

/**
 * @Author lin.lvhua
 * @Date 2023/2/8 14:41
 * @Version 1.0
 * @Description 爬楼梯问题, 如何去理解;
 * 可以爬1步,也可以爬2步;
 * 2:两种
 * 1:一种
 * 3:
 */
public class ClimbingStairsDemo01 {

    public static void main(String[] args) {
        int n = 3;
        int sum = climbStairsThree(n);
        System.out.println("sum = " + sum);
    }

    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }


    public static int climbStairsTwo(int n) {

        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairsTwo(n - 1) + climbStairsTwo(n - 2);
    }

    public static int climbStairsThree(int n) {
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
