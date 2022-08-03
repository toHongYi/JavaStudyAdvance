package com.hongyi.threadDemo.provider_consumer;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/2 21:19
 * @description: TODO
 */
public class Test {
    public static void main(String[] args) {

        Store store = new Store();

        int m = 100;
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < m; i++) {
                    try {
                        store.push(i + "");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < m; i++) {
                    try {
                        String result = store.pull();
                        System.out.println("result:" + result);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
