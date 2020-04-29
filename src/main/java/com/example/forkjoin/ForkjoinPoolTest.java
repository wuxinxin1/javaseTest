package com.example.forkjoin;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * ForkjoinPool 测试
 */
public class ForkjoinPoolTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        forkJoinPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        }).get();
    }

}
