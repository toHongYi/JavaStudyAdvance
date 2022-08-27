package com.atguigu.linkedlist;

import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/27 17:36
 * @description:
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        // 入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        // 出栈
        while (stack.size()>0){
            System.out.println(stack.pop());    //pop就是将栈的数据取出
        }
    }
}
