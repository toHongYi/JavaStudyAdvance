package com.hongyi.threadDemo;

import java.util.concurrent.*;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/2 21:15
 * @description:
 */
public class ProviderDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });

        String result = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {

                Thread.sleep(2000);
                return "sssss";
            }
        }).get();
        Thread.sleep(5000);
        System.out.println("result = " + result);
    }
}
