package com.leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author lin.lvhua
 * @Date 2023/2/3 11:01
 * @Version 1.0
 * @Description
 */
public class SolutionDemo02 {
    public static void main(String[] args) {

//        String str = "adcbdjcofhflvfvadc";
        String str = "abcabcbb";
        Integer limit = getLengthOfStr(str);
        System.out.println("limit = " + limit);
    }

    private static Integer getLengthOfStr(String s) {
        if (s.length()==0){
            return 0;
        }

        Set<Character> mapEntity = new HashSet<>();
        int length = s.length();
        int maxRange = 0;
        // 右指针
        int right = 0;

        for (int i = 0; i < length; i++) {
            if (i != 0) {
                mapEntity.remove(s.charAt(i-1)); // 移动左指针指向
            }

            while ( right<length && !mapEntity.contains(s.charAt(right))){
                mapEntity.add(s.charAt(right)); // value是用不到的,设置值;
                right++; // 移动右指针
            }
            maxRange = Math.max(maxRange,right-i);
        }
        return maxRange;
    }
}
