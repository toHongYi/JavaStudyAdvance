package com.async.forkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Author lin.lvhua
 * @Date 2023/1/16 14:52
 * @Version 1.0
 * @Description fork/join并行任务执行
 * 计算 1,2,3,4......1000所有的值的合
 */
public class ForkJoinExample {

    public static void main(String[] args) {
        // 创建任务
        CalcForJoinTask forJoinTask = new CalcForJoinTask(1, 1000);

        // 创建执行任务的容器,线程池
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> taskFuture = pool.submit(forJoinTask);

        try {
            Integer result = taskFuture.get();
            System.out.println("result = " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        pool.shutdown();
    }

    // 针对一个数字,进行计算
    private static final Integer MAX = 100;

    static class CalcForJoinTask extends RecursiveTask<Integer> {

        private Integer startValue; // 子任务的开始计算的值
        private Integer endValue; // 子任务的结束结算的值

        public CalcForJoinTask(Integer startValue, Integer endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        // 计算方式;
        @Override
        protected Integer compute() {

            // 考虑开始和结束的值的区间
            // 一、是否要进行拆分判断; 如果小于设置的最大值,便不进行拆分
            if (endValue - startValue < MAX) {
                System.out.println(Thread.currentThread().getName()+"开始计算:startValue:" + startValue + "; endValue:" + endValue);
                Integer totalValue = 0;
                for (int i = this.startValue; i <= endValue; i++) {
                    totalValue = totalValue + i;
                }
                return totalValue;
            }

            // 对子任务进行拆分,拆成两个任务;【再进行调用,类似于递归】
            System.err.println("=====任务分解======");
            // 一: 拆分
            CalcForJoinTask subTaskOne = new CalcForJoinTask(startValue, (startValue + endValue) / 2);
            subTaskOne.fork(); // 开线程;

            // 二: 拆分
            CalcForJoinTask subTaskTwo = new CalcForJoinTask((startValue + endValue) / 2 + 1, endValue);
            subTaskTwo.fork();

            return subTaskOne.join() + subTaskTwo.join();
        }
    }

}
