package com.example.thread.complete;


import java.util.concurrent.CompletableFuture;

/**
 *  异步测试
 * @author wuxinxin
 */
public class Test1 {


    public static void main(String[] args) {

        /**
         * CompletableFuture
         */
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();


        CompletableFuture<Void> aa = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"---aa");
            }
        });

        aa.join();


    }

}


