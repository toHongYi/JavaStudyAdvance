package com.hongyi.threadDemo;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/18 22:14
 * @description: 基础多线程;无返回值;
 */
public class Main2 {
    public static void main(String[] args) {
//        sendMail(); 一个人

        //单线程发送
//        for (int i = 0; i <10; i++) {
//            sendMail();
//        }
        // 多线程发送
        for (int i = 0; i <10; i++) {
            new Thread(()->{
               sendMail();
            }).start();
        }

        //防止主线程推出
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟发送邮件;
     */
    public static void sendMail(){
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("发送成功");
    }
}
