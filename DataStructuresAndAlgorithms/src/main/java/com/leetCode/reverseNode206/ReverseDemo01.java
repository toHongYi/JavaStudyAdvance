package com.leetCode.reverseNode206;

/**
 * @Author lin.lvhua
 * @Date 2023/2/16 9:28
 * @Version 1.0
 * @Description
 */
public class ReverseDemo01 {
    public static void main(String[] args) {
        // 先创建节点
        ListNode hero4 = new ListNode(4, null);
        ListNode hero3 = new ListNode(3, hero4);
        ListNode hero2 = new ListNode(2, hero3);
        ListNode hero1 = new ListNode(1, hero2);

        ListNode reverseList = reverseList(hero1);
        System.out.println("reverseList = " + reverseList);
    }

    /**
     * 1、定义函数
     * 2、中止事件
     * 3、
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {

        //递归终止条件是当前为空，或者下一个节点为空
        if (head == null || head.next == null) {
            return head;
        }

        //这里的cur就是最后一个节点
        ListNode cur = reverseList(head.next);
        //这里请配合动画演示理解
        //如果链表是 1->2->3->4->5，那么此时的cur就是5
        //而head是4，head的下一个是5，下下一个是空
        //所以head.next.next 就是5->4
        head.next.next = head;
        //防止链表循环，需要将head.next设置为空
        head.next = null;
        //每层递归函数都返回cur，也就是最后一个节点
        return cur;


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
    }
}
