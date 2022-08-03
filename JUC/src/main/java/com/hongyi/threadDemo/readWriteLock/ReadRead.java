package com.hongyi.threadDemo.readWriteLock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/2 23:40
 * @description: 两个共享锁
 */
public class ReadRead {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void get(Thread thread){
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();

            while (System.currentTimeMillis()-start<=1){
                System.out.println(thread.getName()+"Now Read Operation Happening");
            }
            System.out.println(thread.getName()+"Over Read Operation Done");

        }catch (Exception e){

            System.out.println("出现了异常"+e.getMessage());
        }finally {
            rwl.readLock().unlock();
        }
    }


    public static void main(String[] args) {
        final ReadRead test = new ReadRead();

        new Thread(){
            @Override
            public void run() {
                test.get(currentThread());
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                test.get(currentThread());
            }
        }.start();

    }

}
