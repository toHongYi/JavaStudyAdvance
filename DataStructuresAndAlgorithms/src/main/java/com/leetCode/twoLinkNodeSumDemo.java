package com.leetCode;

import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * @Author lin.lvhua
 * @Date 2023/2/2 15:08
 * @Version 1.0
 * @Description
 */
public class twoLinkNodeSumDemo {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
//        ListNode listNode2 = new ListNode(4);
//        ListNode listNode3 = new ListNode(3);
//        ListNode listNode31 = new ListNode(3);
//        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode31;

        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(9);
        ListNode listNode6 = new ListNode(9);
        ListNode listNode7 = new ListNode(9);
        ListNode listNode8 = new ListNode(9);
        ListNode listNode9 = new ListNode(9);
        ListNode listNode10 = new ListNode(9);
        ListNode listNode11 = new ListNode(9);
        ListNode listNode12 = new ListNode(9);
        ListNode listNode13 = new ListNode(9);
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode9;
        listNode9.next = listNode10;
        listNode10.next = listNode11;
        listNode11.next = listNode12;
        listNode12.next = listNode13;

        ListNode listNode = addTwoNumbers(listNode1, listNode4);

        showNode(listNode);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        BigInteger resultOne = getNums(l1);
        BigInteger resultTwo = getNums(l2);

        System.out.println("resultOne = " + resultOne);
        System.out.println("resultTwo = " + resultTwo);

        String result = String.valueOf(resultOne.add(resultTwo));
        String[] splitResult = result.split("");
        splitResult = doReverse(splitResult);

        ListNode targetNodes = new ListNode(Integer.valueOf(splitResult[0]));
        ListNode doNodes = targetNodes;

        for (int i = 1; i < splitResult.length; i++) {

            Integer s = Integer.valueOf(splitResult[i]);
            ListNode listNode = new ListNode(s);
            doNodes.next = listNode;
            doNodes = listNode;
        }
        return targetNodes;
    }

    /**
     * 功能: 字符串反转
     *
     * @param result
     */
    private static String[] doReverse(String[] result) {
        int length = result.length;
        String[] str = new String[length];
        for (int i = length - 1, j = 0; i >= 0; i--, j++) {
            str[j] = result[i];
        }
        return str;
    }

    private static BigInteger getNums(ListNode l1) {
        // 先取出第一个;
        ListNode target = l1;
        double one = target.val;
        int n = 1;

        // 需要一个遍历链表的方法;
        while (target.next != null) {
            one = one + target.next.val * Math.pow(10, n);
            n++;
            target = target.next;
        }
        String format = new DecimalFormat("0").format(one);
        return BigInteger.valueOf(Long.parseLong(format));
    }


    private static void showNode(ListNode listNode) {
        int i = 0;
        System.out.println("头结点 = " + listNode.val);
        // 获取所有层级,根据层级进行遍历

        ListNode temp = listNode.next;
        while (true){
            if (temp == null){
                break;
            }
            System.out.println("递归进行中部分结点"+ temp.val);
            temp = temp.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public void show() {
            System.out.println("val"+val);
        }
    }
}
