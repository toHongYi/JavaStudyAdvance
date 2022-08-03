package com.hongyi.threadDemo.reentrantLock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/2 22:43
 * @description: 使用共同的锁资源;
 */
public class TestReentrantLock2 {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    Lock lock = new ReentrantLock();

    void insert(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName()+"currentThread Haved release");
        }catch (Exception e){
            System.out.println(thread.getName()+"Now A DangerSign Happen");
        }finally {
            System.out.println(thread.getName()+"release Lock");
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        TestReentrantLock2 test = new TestReentrantLock2();

        new Thread() {
            @Override
            public void run() {
                test.insert(currentThread());
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                test.insert(currentThread());
            }
        }.start();
    }

}
