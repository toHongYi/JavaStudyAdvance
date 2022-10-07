package com.async;

import static com.async.AsyncDemo.exectMethod;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/7 19:57
 * @description: 尝试加点儿东西:
 * 多线程发送;
 * // 主线程退出问题;
 * 下一步:多线程对统一资源进行的处理;
 */
public class Dem002 {
    public static void main(String[] args) throws InterruptedException {

        // 2、基础异步;
        System.out.println("第一次");
        System.out.println("第二次");

        // 五个线程处理
        for (int i = 0; i < 5; i++) {
            startThread();
        }

        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());

        Thread.sleep(100);
        System.out.println("第四次");
    }

    // 开启新线程方法:
    private static void startThread() {

        new Thread(() -> {
            System.out.println("这是线程:" + Thread.currentThread().getName());
            exectMethod();
        }).start();

    }
}
