package com.hongyi.threadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/18 22:57
 * @description: 自定义线程池;
 */
public class Main5 {
    public final static Random random = new Random();

    /**
     * 自定义线程池
     */
    // 10  101  1   若超出任务队列,便会先增加当前核心线程数;
    // 11  100      当先线程数会变成11，任务队列依然是1001;
    // 11  0        慢慢变少,
    // 10  0  闲置一分钟会进行恢复;
    /**
     * 一个线程占用的内存大概在1-2M,1000个线程的话,还是很占内存的;
     * CPU密集计算场景
     * 线程数保存和CPU核心数一致最好
     * 大多的话超出的线程反而会减慢计算速度
     */
    public final static ExecutorService e = new ThreadPoolExecutor(
            10, /** 核心线程数 */
            100, /** 最大线程数  任务数 超出队列   【设置为最大是因为通过阻塞,一直等待,不占资源】*/
            1,TimeUnit.MINUTES, /** 线程超出核心线程数 闲置多久后释放资源 */
            new SynchronousQueue<>()); /** 任务队列如果设置为0, 只要不够,核心线程数就一直网上扩到最大|超过便抛出异常 */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("sendAllMail() = " + sendAllMail());
        // 释放线程池资源,不然会一直阻塞;
        e.shutdown();
    }

    /**
     * 返回发送成功次数
     * @return
     */
    public static int sendAllMail(){
        // 异步队列
        List<CompletableFuture<Boolean>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // 携带返回值的静态调用
            // 一行即可       CompletableFuture.supplyAsync(Main3::sendAllMail);
            // 一行即可       CompletableFuture.supplyAsync(Main3::sendAllMail,e); 指定线程池
            futureList.add(CompletableFuture.supplyAsync(()->{
                return sendMail();
            },e));
        }

        // 使主线程等待所有的都执行完毕,所有异步方法加入到主线程 【未来时】未来才回会有结果的;
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();

        AtomicInteger i = new  AtomicInteger(0);

        // 便利集合;
        futureList.forEach((future)->{
            try {
                if (future.get())  i.addAndGet(1);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        return i.get();


    }

    /**
     * 模拟发送邮件; 具有返回值
     */
    public static boolean sendMail(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        // 模拟发送成功
        if (random.nextBoolean()){
            System.out.println("发送成功");
            return true;
        }
        // 模拟发送失败
        System.out.println("发送失败");
        return false;

    }
}
