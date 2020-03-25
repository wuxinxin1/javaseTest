package com.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 * 起到线程限流的一个作用
 */

public class SemaphoreTest {

    public static void main(String[] args) {

        final Semaphore semaphore = new Semaphore(8);


        ExecutorService executorService = Executors.newFixedThreadPool(30);


        for(int i=0;i<20;i++){

            executorService.execute(new Work2(i,semaphore));

        }

        executorService.shutdown();
    }

}

class Work2 implements Runnable{

    private int i;

    private Semaphore semaphore;


    public Work2(int i, Semaphore semaphore) {
        this.i = i;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(i);
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        semaphore.release();
    }
}
