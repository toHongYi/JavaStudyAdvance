package com.leetCode.dp;

/**
 * @Author lin.lvhua
 * @Date 2023/2/15 9:09
 * @Version 1.0
 * @Description : 获取斐波那契数列
 */
public class Dp70demo02 {
    public static void main(String[] args) {
        int[] fib = fib(25);
        int[] fib2 = getFibArr(25);

        System.out.println("fib = " + fib);
        System.out.println("fib2 = " + fib2);
    }

    // 非递归实现
    public static int[] fib(int n){
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < n; i++) {
            arr[i] = arr[i-1] +arr[i-2];
        }
        return arr;
    }

    // 递归实现
    public static int[] getFibArr(int n){
        int[] arr = new int[n];

        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < n; i++) {
            arr[i] = getFib(i);
        }

        return arr;
    }


    // 通过递归实现
    // 返回斐波那契下标的数值
    public static int getFib(int n){

        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 1;
        }
        return getFib(n-1)+getFib(n-2); // 递归调用
    }

/**
 * 试着在计算机中进行模拟运算
 *
 */


}
