package com.hongyi.threadDemo;

import java.util.concurrent.*;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/12 23:38
 * @description: TODO
 */
public class ThreadDemo {
    public static void main(String[] args) {
        try {

            test();
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    public static void test() throws InterruptedException, ExecutionException {

        Executor executor = Executors.newFixedThreadPool(3);
        CompletionService<String> service = new ExecutorCompletionService<>(executor);
        service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "HelloWorld--" + Thread.currentThread().getName();
            }
        });
    }

}
