package com.async;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static com.async.AsyncDemo.exectMethod;
import static com.async.AsyncDemo.exectMethod2;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/7 20:08
 * @description:
 *      Future会接收发送完毕后的数据;
 *
 *      https://blog.csdn.net/qq_31865983/article/details/106137777?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522166514494216782388091269%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=166514494216782388091269&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-106137777-null-null.142^v51^pc_rank_34_2,201^v3^control_1&utm_term=CompletableFuture&spm=1018.2226.3001.4187
 */
public class Demo03Future {



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 构造一个线程池
        ExecutorService executorServiceImpl = Executors.newSingleThreadExecutor();

        // 3、主线程等待异步回调;
        System.out.println("第一次");
        System.out.println("第二次");

        // 直接调用submit方法进行处理;
        // 根据返回方法进行处理;
        Future<?> submit = executorServiceImpl.submit(() -> {
            exectMethod();
        });

        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());

        Thread.sleep(100);
        System.out.println("第四次");
        System.out.println("submit.get() = " + submit.get());
        System.out.println("主线程返回子线程方法");

        // 关闭;
        System.out.println("submit.isDone() = " + submit.isDone());
        // 关闭线程池
        executorServiceImpl.shutdown();
    }

    // 开启新线程方法:
    private static void startThread() {

        new Thread(() -> {
            System.out.println("这是线程:" + Thread.currentThread().getName());
            exectMethod();
        }).start();

    }

    @Test
    public void test3() throws Exception {
        // 创建异步执行任务:
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        Future<Double> cf = executorService.submit(()->{
            System.out.println(Thread.currentThread()+" start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(false){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+" exit,time->"+System.currentTimeMillis());
                return 1.2;
            }
        });
        System.out.println("main thread start,time->"+System.currentTimeMillis());
        //等待子任务执行完成,如果已完成则直接返回结果
        //如果执行任务异常，则get方法会把之前捕获的异常重新抛出
        System.out.println("run result->"+cf.get());
        System.out.println("main thread exit,time->"+System.currentTimeMillis());
    }

    @Test
    public void test2() throws Exception {
        // 创建异步执行任务，有返回值
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+" start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(false){ // 改成true 校验异常处理
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+" exit,time->"+System.currentTimeMillis());
                return 1.2;
            }
        });
        System.out.println("main thread start,time->"+System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->"+cf.get());
        System.out.println("main thread exit,time->"+System.currentTimeMillis());
        // 关闭;
    }

    @Test
    public void test4() throws Exception {
        // 创建异步执行任务，无返回值
        CompletableFuture cf = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread()+" start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(false){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+" exit,time->"+System.currentTimeMillis());
            }
        });
        System.out.println("main thread start,time->"+System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->"+cf.get());
        System.out.println("main thread exit,time->"+System.currentTimeMillis());
    }


    // 异步回调
    @Test
    public void test5() throws Exception {
        ForkJoinPool pool=new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+" start job1,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job1,time->"+System.currentTimeMillis());
            return 1.2;
        },pool);
        //cf关联的异步任务的返回值作为方法入参，传入到thenApply的方法中
        //thenApply这里实际创建了一个新的CompletableFuture实例
        CompletableFuture<String> cf2=cf.thenApply((result)->{
            System.out.println(Thread.currentThread()+" start job2,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job2,time->"+System.currentTimeMillis());
            return "test:"+result;
        });
        System.out.println("main thread start cf.get(),time->"+System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->"+cf.get());
        System.out.println("main thread start cf2.get(),time->"+System.currentTimeMillis());
        System.out.println("run result->"+cf2.get());
        System.out.println("main thread exit,time->"+System.currentTimeMillis());
    }
}
