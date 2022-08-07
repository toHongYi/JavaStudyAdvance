package com.atguigu.queue;

import java.util.Scanner;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/7 20:37
 * @description: TODO
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        System.out.println("测试数据模拟环形队列");

        // 创建一个环形队列
        CircleArray queue = new CircleArray(5); //说明设置4,其队列的有效数据最大是3
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in); //接收
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0); //接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':   //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':   //查看队列头
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':   //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

// 使用数组模拟队列—编写一个ArrayQueue类
class CircleArray {

    private int maxSize;    //表示数组的最大容量
    // front 变量的含义做一个调整:front就指向队列的第一个原始,也就是说arr[front]
    // front 变量的初始值 = 0
    private int front;    //队列头
    // rear 变量的含义做一个调整: rear指向队列最后一个元素的位置,因为希望空出
    // rear 的初始值 = 0
    private int rear;    //队列尾
    private int[] arr;    //该数组用于存放数据,模拟队列


    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
//            front = 0;
//            rear = 0; 默认为0,减少赘余;

    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满,不能加入数据");
            return;
        }
        // 直接将数据加入
        arr[rear] = n;
        //将rear 后移,这里必须考虑取模;
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据,出队列
    public int getQueue() {
        if (isEmpty()) {
//            return -1; 不可
            // 抛异常结束
            throw new RuntimeException("队列为空,不能取数据");
        }
        // 这里需要分析出front是指向队列的第一个元素
        // 1、先把front对应的值保留到一个临时变量
        // 2、将 front 后移, 考虑取模
        // 3、将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示队列的所有数据
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            System.out.println("队列空的没有数据~~");
        }
        // 思路: 从front开始遍历,遍历多少个元素
        // 动脑筋
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 求出当前队列有效数据个数
    public int size() {
        // rear = 1
        // front = 0
        // maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头元素,注意不是取出数据
    public int headQueue() {
        // 遍历
        if (isEmpty()) {
            throw new RuntimeException("队列空的没有数据~~");
        }
        return arr[front];
    }

}
