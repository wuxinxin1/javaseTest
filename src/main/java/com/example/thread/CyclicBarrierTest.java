package com.example.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wuxinxin
 * 同步屏障测试
 * 同步屏障:为CyclicBarrier设置一个整形值，如果达到cyclicBarrier.await()的调用次数，那么所有线程执行cyclicBarrier.await()后面的代码
 * 否则都停留等待cyclicBarrier.await()之前的代码；当到达屏障匹配数量时，可以优先执行一个定义好的任务
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });

        //下面这样执行无效，主线程被堵住了
        //cyclicBarrier.await();

        //cyclicBarrier.await();



        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("aa");
                }
            }).start();
        }
        cyclicBarrier.await();
    }
}
