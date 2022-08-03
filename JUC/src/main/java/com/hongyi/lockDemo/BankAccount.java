package com.hongyi.lockDemo;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/2 21:12
 * @description: TODO
 */
public class BankAccount {
    // 账户余额
    private double balance = 100.0;

    // 存钱
    public synchronized void saveMoney(double money){
        balance = balance + money;
    }

    // 减钱
    public void drawMoney(double money){
        synchronized (this){
            balance = balance -money;
        }

    }


    public double getBalance() {
        return balance;
    }
}
