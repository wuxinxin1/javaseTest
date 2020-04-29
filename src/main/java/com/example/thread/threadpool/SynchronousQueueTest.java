package com.example.thread.threadpool;

import java.util.concurrent.SynchronousQueue;

/**
 * @author wuxinxin
 *
 * 测试SynchronousQueue
 */
public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {

        SynchronousQueue synchronousQueue = new SynchronousQueue();

        boolean offer = synchronousQueue.offer(new Object());

        System.out.println(offer);

        Object take = synchronousQueue.take();

        System.out.println(take);
    }

}
