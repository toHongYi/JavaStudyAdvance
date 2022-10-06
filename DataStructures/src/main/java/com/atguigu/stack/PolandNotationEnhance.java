package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/6 18:25
 * @description: 将一个中缀表达式转化为后缀表达式
 * <p>
 * 1) 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
 * 2) 从左至右扫描中缀表达式；
 * 3) 遇到操作数时，将其压s2；
 * 4) 遇到运算符时，比较其与s1栈顶运算符的优先级：
 * 1.如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
 * 2.否则，若优先级比栈顶运算符的高，也将运算符压入s1；
 * 3.否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
 * 5) 遇到括号时：
 * (1) 如果是左括号“(”，则直接压入s1
 * (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
 * 6) 重复步骤2至5，直到表达式的最右边
 * 7) 将s1中剩余的运算符依次弹出并压入s2
 * 8)  依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
 */
public class PolandNotationEnhance {
    public static void main(String[] args) {
        // 完成将一个中缀表达式转成后缀表达式的功能
        // 说明:
        // 1.  1+((2+3)*4)-5 => 转成  1 2 3 + 4 * + 5 -
        // 2.  因为直接对str 进行操作，不方便，因此先将字符串 "1+((2+3)*4)-5"  =》中缀的表达式对应的一个list;
        //     既"1+((2+3)*4)-5" =》 ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        // 3.将得到的中缀表达式的List =》 后缀表达式对应的List
        //     即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList[1,2,3,+,4,*,+,5,-]

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("后缀表达式对应的List" + infixExpressionList); // ArrayList [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List" + suffixExpressionList); // List[1, 2, 3, +, 4, *, +, 5, -]

        System.out.printf("expression=%d",calculate(suffixExpressionList)); // ?
        System.out.println("");
    }





    // 即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList[1,2,3,+,4,*,+,5,-]
    // 方法: 将得到的中缀表达式转化为后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        // 定义两个栈
        Stack<String> s1 = new Stack<String>(); // 符号栈
        // 说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还要逆序输出、
        // 因此比较麻烦，这里我们就不用Stack<String> 直接使用List<String> s2
//        Stack<String> s2 = new Stack<String>(); // 存储中间结果的栈s2
        List<String> s2 = new ArrayList<String>(); // 存储中间结果的栈list2

        // 遍历ls
        for (String item : ls) {
            // 如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号")",则依次弹出s1栈顶的运算符,并加入s2，直到遇到左括号为止，此时这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); // !!很重要,将"("小括号进行弹出s1栈，消除小括号
            } else {
                // 当s1栈顶的运算符的若优先级小于等于栈顶运算符,将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                // 我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && (Operation.getValue(s1.peek())) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 还需要将item压入栈
                s1.push(item);
            }
        }

        // 将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;  // 注意因为是存放到List,因此按顺序输出就是对应的后缀表达式对应的List
    }

    // 编写一个方法：将中缀表达式转成对应的List
    // s="1+((2+3)*4)-5";
    public static List<String> toInfixExpressionList(String s) {
        // 定义一个List,存放中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0; // 这时是一个指针,用于遍历 中缀表达式字符串
        String str; // 对多位数的一个拼接
        char c;     // 每遍历到一个字符,就放入到c
        do {
            // 如果c是一个非数字,我们就需要加入到ls
            if (((c = s.charAt(i)) < 48) || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++; // i需要后移
            } else { // 如果是一个数，需要考虑多位数问题
                str = ""; // 先将str置成 ""   '0'[48]->'9'[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c; // 拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }


    // 将逆波兰表达式，一次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        // 将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    // 完成对逆波兰表达式的运算

    /**
     * 1):从左到右扫描,将3和4压入堆栈;
     * 2)：遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
     * 3)：将5入栈；
     * 4):接下来是*运算符，因此弹出5和7，计算出7*5=35，将35入栈；
     * 5):将6入栈；
     * 6):最后是-运算符，计算出35-6的值，即29，因此得出最终结果
     */

    public static int calculate(List<String> ls) {
        // 创建给栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        // 遍历ls
        for (String item : ls) {
            // 使用正则表达式取出数
            if (item.matches("\\d+")) { //匹配的是多位数
                // 入栈
                stack.push(item);
            } else {
                // pop出两个数并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                // 把res入栈 [整数转字符串:任何类型+字符串即为字符串]
                stack.push("" + res);
            }
        }
        // 最后留在stack中的结果就是运算结果
        return Integer.parseInt(stack.pop());
    }
}

// 编写一个类Operation，可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    // 撰写一个方法,返回对应的优先级;
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算");
                break;
        }
        return result;
    }


}
