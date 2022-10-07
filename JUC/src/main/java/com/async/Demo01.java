package com.async;

import static com.async.AsyncDemo.exectMethod;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/7 19:08
 * @description: 基础异步执行思想;
 */
public class Demo01 {

    public static void main(String[] args) throws InterruptedException {

        // 1、基础异步;
        System.out.println("第一次");
        System.out.println("第二次");

        // 在这里就是异步执行;
        startThread();
        System.out.println("第三次");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());

        Thread.sleep(800);
        System.out.println("第四次");



    }

    // 开启新线程方法:
    static void startThread(){

        new Thread(()->{
            exectMethod();
        }).start();

    }

}
