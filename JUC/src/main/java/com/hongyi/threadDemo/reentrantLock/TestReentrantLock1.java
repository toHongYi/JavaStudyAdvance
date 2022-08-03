package com.hongyi.threadDemo.reentrantLock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/2 21:50
 * @description: 这是因为每个线程都new出了一个锁，因此他们互不影响，没有满足线程安全的需求。
 */
public class TestReentrantLock1 {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public void insert(Thread thread) {
        Lock lock = new ReentrantLock();    //注意这个地方
        lock.lock();
        try {
            System.out.println(thread.getName() + "得到了锁");
            for (int i = 0; i < 1000; i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println(thread.getName() + "释放了锁");
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        TestReentrantLock1 test = new TestReentrantLock1();

        new Thread() {
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();

        new Thread() {
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();
    }
}
