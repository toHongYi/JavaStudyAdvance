package com.hongyi.threadDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/18 22:17
 * @description: 未来时
 */
public class Main3 {
    public final static Random random = new Random();

    public static void main(String[] args) {
        System.out.println(sendAllMail());

    }

    /**
     * 返回发送成功次数
     * @return
     */
    public static int sendAllMail(){
        List<CompletableFuture<Boolean>> futureList = new ArrayList<>();
        //默认现场池会对当前设备进行判断;useCommonPool
        for (int i = 0; i < 64; i++) {
            // 携带返回值的静态调用
            // 一行即可       CompletableFuture.supplyAsync(Main3::sendAllMail);
            futureList.add(CompletableFuture.supplyAsync(()->{
                return sendMail();
            }));
        }

        // 使主线程等待所有的都执行完毕,所有异步方法加入到主线程 【未来时】未来才回会有结果的;
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();

        AtomicInteger i = new  AtomicInteger(0);
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
