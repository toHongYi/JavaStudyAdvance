package com.async;

import org.springframework.stereotype.Component;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/7 19:43
 * @description:
 */
@Component
public class AsyncDemo {

    // 无返回值,计算方法,数据返回;
    static void exectMethod() {
        try {
            String currentThread = Thread.currentThread().getName();
            System.out.println("currentThread = " + currentThread);
            Thread.sleep(800);
            System.out.println("线程执行完毕,数据已更新入库");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 有返回值,计算方法,数据返回;
    static boolean exectMethod2() {
        try {
            String currentThread = Thread.currentThread().getName();
            System.out.println("currentThread = " + currentThread);
            Thread.sleep(800);
            System.out.println("线程执行完毕,数据已更新入库");
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
