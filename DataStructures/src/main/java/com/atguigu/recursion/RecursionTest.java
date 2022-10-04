package com.atguigu.recursion;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/4 13:31
 * @description: 递归与阶乘
 */
public class RecursionTest {
    public static void main(String[] args) {

        // 通过打印问题,回顾递归调用机制
//        test(4);

        // 阶乘
        System.out.println("factorial(3)[1 * 2 * 3] = " + factorial(3));
    }

    // 递归的初尝试;打印问题
    // 注意程序执行在JVM中的流动;
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

    // 阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;  // 1 * 2 * 3
        }
    }
}
