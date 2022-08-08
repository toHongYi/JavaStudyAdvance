package com.hongyi.atomicDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/8 15:04
 * @description: TODO
 */
public class AtomicRunnable implements Runnable{

    private static int count;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i=0;i<10000;i++){
            count++;
            atomicInteger.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        Runnable runnable = new AtomicRunnable();
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(runnable) ;
            threads[i].start();
        }
        Thread.sleep(3000);
        System.out.println(count);
        System.out.println(atomicInteger);
    }
//    版权声明：本文为CSDN博主「T_Antry」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/qq_39150049/article/details/113516453

}
