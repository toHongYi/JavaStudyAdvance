package com.hongyi.threadDemo.conditionLock;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/3 20:45
 * @description: TODO
 */
public class UseSingleConditionWaitNotify2 {
    public static void main(String[] args) throws InterruptedException {
        MyserviceMoreCondition service = new MyserviceMoreCondition();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
        Thread.sleep(3000);
        service.signalAll_A();
    }
    static public class ThreadA extends Thread {
        private MyserviceMoreCondition service;
        public ThreadA(MyserviceMoreCondition service) {
            super();
            this.service = service;
        }
        @Override
        public void run() {
            service.awaitA();
        }
    }
    static public class ThreadB extends Thread {
        private MyserviceMoreCondition service;
        public ThreadB(MyserviceMoreCondition service) {
            super();
            this.service = service;
        }
        @Override
        public void run() {
            service.awaitB();
        }
    }
}
