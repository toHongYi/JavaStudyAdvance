package com.leetCode.valid20;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author lin.lvhua
 * @Date 2023/2/8 9:06
 * @Version 1.0
 * @Description
 */
public class ValidParentheses {
    public static void main(String[] args) {
        String str = "()[]{}";
//        String str = "(([[{{";
//        boolean valid = isValid2(str);
        boolean valid = isValid(str);
        System.out.println("valid = " + valid);
    }

    /**
     *   借助栈结构进行实现,其实现核心在于,通过栈
     *   push: 压栈
     *   pop:  出栈
     *   seek: 查看栈顶
     * @param s
     * @return
     */
    public static boolean isValid(String s) {

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        int n = s.toCharArray().length;
        // 奇数直接不成立
        if (n % 2 > 0) {
            return false;
        }

        Deque<Character> stack = new LinkedList<Character>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i); // 取出左字串
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop(); // 出栈
            } else {
                stack.push(ch); // 入栈
            }
        }
        return stack.isEmpty();
    }


    public static boolean isValid1(String s) {
        char[] sChar = s.toCharArray();
        int n = s.toCharArray().length;
        // 奇数直接不成立
        if (n % 2 > 0) {
            return false;
        }

        // 不包含直接不成立
        HashMap<Character, Character> pairs = new HashMap<>();
        pairs.put('(', ')');
        pairs.put('{', '}');
        pairs.put('[', ']');

        for (int i = 0; i < sChar.length / 2; i++) {
            s = s.replace("()", "")
                    .replace("[]", "")
                    .replace("{}", "");
        }
        return true;
    }


    public static boolean isValid2(String s) {
        char[] sChar = s.toCharArray();
        if (sChar.length % 2 > 0) { // 奇数直接不成立
            return false;
        }

        int i = 0;
        while (i < sChar.length) {

            if (sChar[i] != sChar[i + 1]) {
                return false;
            }
            i++;
            i++;
        }
        return true;

    }

}
