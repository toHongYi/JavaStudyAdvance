package com.hongyi.threadDemo;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/18 22:11
 * @description: TODO
 */
public class Main {
    public static void main(String[] args) {
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("你好");
        }).start();

        System.out.println("世界");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
