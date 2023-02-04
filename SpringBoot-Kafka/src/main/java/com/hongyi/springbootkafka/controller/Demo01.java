package com.hongyi.springbootkafka.controller;

import java.util.HashSet;
import java.util.Set;

/**
 * @author HongYi
 * @version 1.0
 * @date 2023/2/4 8:49
 * @description:
 */
public class Demo01 {
    public static void main(String[] args) {
        String str = "pwwkew";
        int i = lengthOfLongestSubstring(str);
        System.out.println("i = " + i);
    }

    public static int lengthOfLongestSubstring(String s) {
        int right = 0;
        Set<Character> entity = new HashSet<>();
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i!=0){
                entity.remove(s.charAt(i-1));
            }
            while (right < s.length() && !entity.contains(s.charAt(right))) {
                entity.add(s.charAt(right));
                max = Math.max(max,right-i+1);
                right++;
            }

        }

        return max;
    }

}
