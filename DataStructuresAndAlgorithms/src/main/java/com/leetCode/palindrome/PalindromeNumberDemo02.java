package com.leetCode.palindrome;

/**
 * @Author lin.lvhua
 * @Date 2023/2/7 16:00
 * @Version 1.0
 * @Description
 */
public class PalindromeNumberDemo02 {
    public static void main(String[] args) {

        boolean palindrome = isPalindrome(2332);
        System.out.println("palindrome = " + palindrome);
    }

    public static boolean isPalindrome(int x) {
        //边界判断
        if (x < 0) {
            return false;
        }
        int div = 1;
        //
        while (x / div >= 10) {
            div *= 10;
        }
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) {
                return false;
            }
            x = (x % div) / 10;
            div /= 100; // 需要考虑的中间变量，左右各取出一组最终得到的便是去除100
        }
        return true;

    }
}
