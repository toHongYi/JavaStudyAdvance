package com.hongyi.lockDemo;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/2 21:11
 * @description: TODO
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {

        BankAccount bankAccount = new BankAccount();
        int m = 1000;

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < m; i++) {
                    bankAccount.saveMoney(10);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < m; i++) {
                    bankAccount.drawMoney(10);
                }
            }
        });

        // 启动两个线程
        thread1.start();
        thread2.start();

        // 等待上边两个线程结束
        Thread.sleep(10000);

        // 获取账户余额
        System.out.println("balance:" + bankAccount.getBalance());


    }
}
