package com.atguigu.stack;


/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/6 14:53
 * @description: 使用栈完成一个表达式的计算思路;
 * 1.通过一个index值(索引)，来遍历我们的表达式
 * 2.如果我们发现是一个数字，就直接入数栈
 * 3.如果我发现扫描到的是一个符号，就分如下情况
 * 3.1 如果发现当前的符号栈为空，就直接入栈；
 * 3.2 如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或等于栈中的操作符，就需要从数栈中pop出两个数，再从符号栈中pop出一个符号，进行运算；
 * 将得到的结果，入数栈，然后将当前的操作符入符号栈，如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈。
 * 4.当表达式扫描完毕，就顺序的从数栈和符号栈中取出相应的数和符号，并运行。
 * 5.最后在数栈只有一个数字，就是表达式的结果
 *
 * 【只支持个位数的式子】
 */
public class Calculator {
    public static void main(String[] args) {
        // 根据前面老是的思路,完成表达式的运算;
        String expression = "3+2*6-2"; // 如何处理多位数的问题
//        String expression2 = "70+2*6-2"; = 8【存在非个位数时问题】
        // 创建两个栈,数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //将每次扫描得到的数据保存到ch
        // 开始while循环的扫描expression
        while (true) {
            // 依次得到expression的每一个字符
            // 先截取字符串,再获取截取后字符串的第一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断ch是什么，然后做相对于的处理
            if (operStack.isOper(ch)) {//如果运算符
                // 判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //  3.1 如果发现当前的符号栈为空，就直接入栈；
                    //  3.2 如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或等于栈中的操作符，就需要从数栈中pop出两个数，再从符号栈中pop出一个符号，进行运算；
                    //      将得到的结果，入数栈，然后将当前的操作符入符号栈。
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {

                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //  把运算的结果入数栈
                        numStack.push(res);
                        // 然后把当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        // 如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈。
                        operStack.push(ch);
                    }
                } else {
                    // 如果为空,直接入栈...
                    operStack.push(ch); // 1 + 3
                }
            } else { // 如果是数,就直接入数栈;
                // ASCII 码表对应关系 1:49
                numStack.push(ch - 48); // ? "1+3" '1' => 1 【字符3与十进制3相差48】
            }
            // 让index + 1, 并判断是否扫描到expression最后;
            index++;
            if (index >= expression.length()) {
                break; // 扫描结束;
            }
        }

        // 4.当表达式扫描完毕，就顺序的从数栈和符号栈中取出相应的数和符号，并运行。
        while (true){
            // 如果符号栈为空，则计算到最后的结果，数栈中只有一个数值，这个就是结果;
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        // 将数栈的最后数,pop出去,就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d",expression,res2);
        System.out.println(" ");

    }
}


// 先创建一个栈,直接使用前面创建好的;
// 定义一个标识栈,需要扩展功能;

class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈,数据就放在该数据
    private int top = -1; //表示栈顶,初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //增加一个方法,可以返回当前栈顶的值,但是不是真正的pop
    public int peek() {
        return stack[top];
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
    public void push(int value) {
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

    // 返回运算符的优先级,优先级由程序员来确定,优先级使用数字进行标识
    // 数字越高表示优先级越大
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; //假定目前的表达式只有 +,-,*,/
        }
    }

    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法【只考虑四种场景】
    public int cal(int num1, int num2, int oper) {
        int res = 0; //res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; // 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}
