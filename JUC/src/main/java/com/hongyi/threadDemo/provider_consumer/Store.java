package com.hongyi.threadDemo.provider_consumer;

import java.util.LinkedList;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/2 21:18
 * @description: TODO
 */
public class Store {

    int max = 10;

    private LinkedList<String> queue = new LinkedList<String>();

    public synchronized void push(String str) throws InterruptedException {

        Thread.sleep(1000);

        if (queue.size() == max) {
            System.out.println("当前队列已满--线程等待");
            try {
                // 让当前线程等待，释放当前的锁，带别人唤醒时，接着执行
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(str);

        // 唤醒其他等待线程
        notifyAll();
    }

    public synchronized String pull() throws InterruptedException {
        Thread.sleep(1000);

        if (queue.isEmpty()) {
            System.out.println("当前队列为空，程序进入等待");
            // 让当前线程等待，释放当前的锁，带别人唤醒时，接着执行
            wait();
        }
        String str = queue.poll();
        notifyAll();
        return str;
    }

}
