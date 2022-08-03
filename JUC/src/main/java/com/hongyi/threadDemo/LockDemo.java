package com.hongyi.threadDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/2 23:28
 * @description: TODO
 */
public class LockDemo {
    public static void main(String[] args) {

        ReadWriteLock lock = new ReadWriteLock() {
            @Override
            public Lock readLock() {
                return null;
            }

            @Override
            public Lock writeLock() {
                return null;
            }
        };


    }
}
