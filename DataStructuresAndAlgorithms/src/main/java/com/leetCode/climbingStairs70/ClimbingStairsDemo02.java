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
public class ClimbingStairsDemo02 {

    public static void main(String[] args) {
        int n = 5;
        int sum = climbStairs(n);
        System.out.println("sum = " + sum);
    }

    public static int climbStairs(int n) {
        int[] arr = new int[n+1];
        int nn = getClimbStairs(arr, n);
        return nn;
    }

    private static int getClimbStairs(int[] arr, int n) {
        if (arr[n] > 0) {
            return arr[n];
        }
        if (n <= 2) {
            return n;
        }
        arr[n] = getClimbStairs(arr, n - 1) + getClimbStairs(arr, n - 2);
        return arr[n];
    }

}
