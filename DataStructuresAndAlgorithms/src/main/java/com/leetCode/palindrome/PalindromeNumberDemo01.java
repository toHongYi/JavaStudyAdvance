package com.leetCode.palindrome;

/**
 * @Author lin.lvhua
 * @Date 2023/2/7 16:00
 * @Version 1.0
 * @Description
 */
public class PalindromeNumberDemo01 {
    public static void main(String[] args) {

        boolean palindrome = isPalindrome(200);
        System.out.println("palindrome = " + palindrome);
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String str = String.valueOf(x);
        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length/2; i++) {
            if (charArray[i]!=charArray[charArray.length-i-1]){
                return false;
            }
        }

        return true;
    }
}
