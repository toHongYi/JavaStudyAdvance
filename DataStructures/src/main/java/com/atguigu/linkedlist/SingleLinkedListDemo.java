package com.atguigu.linkedlist;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/8 19:12
 * @description:
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 进行一个测试
        // 先创建节点
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        // 加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        // 加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        // 显示一把
        singleLinkedList.list();

        // 测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);

        System.out.println("修改后的输出");
        singleLinkedList.list();


        //删除一个节点
        singleLinkedList.del(1);
        singleLinkedList.del(4);
//        singleLinkedList.del(3);
//        singleLinkedList.del(2);
        System.out.println("删除后的链表情况~~");
        singleLinkedList.list();

        System.out.printf("没有找到编号 %d 的节点,不能修改\n", newHeroNode.no);
        System.out.printf("没有找到编号 %d 的节点,不能修改", newHeroNode.no);
        System.out.printf("没有找到编号  的节点,不能修改\n", newHeroNode.no);

        // 测试一下,求单链表中有效节点的个数
        System.out.printf("有效的节点个数为:%d\n", getLength(singleLinkedList.getHead()));

        // 测试一下，是否的到了倒数第K个节点
        HeroNode res = findLastNode(singleLinkedList.getHead(), 1); //如果没有,就返回null
        System.out.println("res = " + res);

    }

    //查找链表中的倒数第K个结点【新浪面试题】
    //思路

    /**
     * 1、编写一个方法,接收head节点,同时接收一个index
     * 2、index表示的是倒数第index个节点;
     * 3、先把链表从头到尾遍历,得到链表的总的长度 getLength
     * 4、得到size后,我们从链表的第一个开始遍历(size-index)个,就可以得到
     * 5、如果找到了,返回该节点,否则返回null;
     */
    public static HeroNode findLastNode(HeroNode head, int index) {
        //判断如果链表为空,返回null
        if (head.next == null) {
            return null; //没有找到
        }
        // 第一次遍历得到链表的长度(节点个数)
        int size = getLength(head);
        // 第二次遍历 size-index 的位置,就是我们倒数的第K个节点
        // 先做一个index的校验,
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义一个辅助变量 for循环定位到倒数的index
        HeroNode cur = head.next;   // 3 // 3-1
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    // 方法: 获取到单链表的节点的个数(如果是带头节点的链表,需要不统计头节点)

    /**
     * @param head 链表的头节点
     * @return: 有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) { //空链表
            return 0;
        }
        int length = 0;
        // 定义一个辅助的变量,这里没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }
}

// 定义SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    // 初始化一个头节点,头节点不要动,不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    // 返回头节点
    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单链表
     * 但不考虑编号的顺序时
     * 1、找到当前链表的最后节点
     * 2、将最后这个节点的next 指向新节点
     */
    public void add(HeroNode heroNode) {
        //因为headNode不能动,因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        // 遍历链表,找到最后   直到temp为尾节点;
        while (true) {
            // 找到链表的最后; 什么时候说明链表到最后了;
            if (temp.next == null) {
                break;
            }
            // 如果没有找到就将temp后移
            temp = temp.next;
        }
        // 当退出while循环时,temp就指向了链表的最后;
        // 将最后节点的next指向了新的节点
        temp.next = heroNode;
    }

    // 第二种方式在添加英雄时,根据排名将英雄插入指定位置
    //(如果有这个排名,则添加失败,并给出提示)
    public void addByOrder(HeroNode heroNode) {
        // 因为头节点不能动,因此我们仍然通过一个辅助指针(变量)来帮助我们找到添加的位置
        // 因为单链表,因此我们找到的这个temp时位于添加位置前一个节点,否则无法添加;
        HeroNode temp = head;
        boolean flag = false;   // flag标识添加的编号是否存在,默认为false;
        while (true) {
            if (temp.next == null) { //说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {    //位置找到,就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {   //说明希望添加的heroNode已然存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next;   //后移,遍历当前链表;
        }
        //判断flag 的值
        if (flag) { //不能添加,说明编号存在
            System.out.printf("准备插入的英雄的编号%d已经存在了,不能加入\n", heroNode.no);
        } else {
            // 插入到链表,temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    // 修改节点信息,根据no编号来修改,既no编号不能改

    /**
     * 说明:
     * 1、根据newHeroNode 的 no来修改即可
     */
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点,根据no编号
        // 定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;   //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;  // 已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                // 找到
                flag = true;
                break;  // 结束循环
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {//没有找到
            System.out.printf("没有找到编号 %d 的节点,不能修改\n", newHeroNode.no);
        }

    }

    // 显示链表[遍历]
    public void list() {
        // 判断链表是否为null
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动,因此我们需要一个辅助变量就行遍历;
        HeroNode temp = head.next;  //如果不为空,便肯定会有数据;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // 将next节点后移,一定小心
            temp = temp.next;
        }
    }

    // 删除节点

    /**
     * 1、head不能动,因此我们需要一个temp辅助节点找到待删除节点的前一个节点;
     * 2、说明我们在比较时,时temp.next.no 和需要删除的节点的no比较
     */
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; // 标志是否找到待删除节点
        while (true) {
            if (temp.next == null) { //已经到链表最后
                break;
            }
            if (temp.next.no == no) {
                // 找到了待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next; // temp后移,遍历
        }
        // 判断flag
        if (flag) {  //找到
            // 可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的节点 %d 不存在\n", no);
        }

    }

}

// 定义一个HeroNode,每个heroNode对象就是一个节点;
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;   // 指向下一个节点

    //构造器
    public HeroNode(int hNo, String name, String nickName) {
        this.no = hNo;
        this.name = name;
        this.nickName = nickName;
    }

    // 为了显示方便,我们重写toString()
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
//                ", next=" + next +  可以去掉内部嵌套着的数据
                '}';
    }

}
