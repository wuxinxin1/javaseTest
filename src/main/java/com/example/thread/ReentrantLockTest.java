package com.example.thread;


import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁测试
 */
public class ReentrantLockTest {

    private static ReentrantLock reentrantLock=new ReentrantLock();


    public static void main(String[] args) {

        reentrantLock.lock();

        reentrantLock.unlock();

    }


}
