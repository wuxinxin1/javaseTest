package com.example.thread.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {

        ConcurrentLinkedQueue<Object> objects = new ConcurrentLinkedQueue<>();

        System.out.println(objects.offer(1));
        System.out.println(objects.offer(2));




    }

}
