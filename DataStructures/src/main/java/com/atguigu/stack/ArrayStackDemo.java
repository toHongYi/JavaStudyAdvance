package com.atguigu.stack;

import java.util.Scanner;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/27 14:43
 * @description:
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        // 测试一下ArrayStack是否准确;
        // 先创建一个ArrayStack的对象进行测试;
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 表示退出程序");
            System.out.println("push: 表示添加数据到栈");
            System.out.println("pop: 表示从栈取出数(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();

            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是%d\n", res);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

// 定义一个ArrayStack 表示栈
class ArrayStack {
    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈,数据就放在该数据
    private int top = -1; //表示栈顶,初始化为-1

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈顶

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(Integer value) {
        // 先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop,将栈顶的数据返回
    public int pop() {
        // 先判断是否空
        if (isEmpty()) {
            throw new RuntimeException("栈空");   //该异常可直接抛出,不捕获也无问题;
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况【遍历栈】,遍历时需要从栈顶开始显示;
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空,没有数据~~");
            return;
        }
        // 需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

}
