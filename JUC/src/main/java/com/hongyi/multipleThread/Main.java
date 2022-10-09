package com.hongyi.multipleThread;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/18 22:11
 * @description: 配合B站教程: https://www.bilibili.com/video/BV1Ca411q7zv?spm_id_from=333.999.0.0&vd_source=09d85bfdd192ce505e3b496e70b7163f
 */
public class Main {
    public static void main(String[] args) {
        new Thread(() -> {
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
