package com.leetCode.valid20;

import java.util.*;

/**
 * @Author lin.lvhua
 * @Date 2023/2/8 10:32
 * @Version 1.0
 * @Description
 */
public class ValidParenthesesDemo02 {
    public static void main(String[] args) {
        String str = "()[]{}";

        boolean valid = isValid(str);
        System.out.println("valid = " + valid);


    }

    public static boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        // 创建映射Map
        Map<Character, Character> pairs = new HashMap<Character, Character>() {
            {
                put(')', '(');
                put('}', '{');
                put(']', '[');
            }
        };

        // 创建栈结构链表;
        Deque<Character> stack = new LinkedList<Character>();

        for (int i = 0; i < s.length(); i++) {
            if (pairs.containsKey(charArray[i])) {

                if (stack.isEmpty() || stack.peek() != pairs.get(charArray[i])) {
                    return false;
                }

                stack.pop(); // 出栈
            } else {
                stack.push(charArray[i]);
            }

        }

        return stack.isEmpty();
    }
}
