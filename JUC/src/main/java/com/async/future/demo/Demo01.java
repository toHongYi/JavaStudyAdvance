package com.async.future.demo;

import java.util.concurrent.ExecutionException;

/**
 * @Author lin.lvhua
 * @Date 2023/1/16 17:41
 * @Version 1.0
 * @Description
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //异步操作 可以用一个线程池
//        ExecutorService executor = Executors.newFixedThreadPool(1);
//        //执行FutureTask，相当于上例中的 client.request("name") 发送请求
//        //在这里开启线程进行RealData的call()执行
//        final String name = "name";
//        Future<String> future = executor.submit(new RealData(name));
//        System.out.println("请求完毕，数据准备中");
//        try {
//            //这里依然可以做额外的数据操作，这里使用sleep代替其他业务逻辑的处理
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//        }
//        //如果此时call()方法没有执行完成，则依然会等待
//        System.out.println("数据 = " + future.get());


    }
}
