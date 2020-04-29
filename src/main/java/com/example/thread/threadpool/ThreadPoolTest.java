package com.example.thread.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池创建
 *
 * 线程池重要说明:
 *
 * 1.非核心线程，生命一定是有时间限制的，即使不指定allowCoreThreadTimeOut=false
 * 2.核心线程可以设置allowCoreThreadTimeOut=true,用来指定核心线程的最大空闲时间，如果不指定，核心线程永远驻留在内存
 *
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws Exception{

        //创建一个固定大小的线程池
        // 1.创建一个固定大小线程数量的线程池，coreSize和maxSize一样大
        // 2.超过了coreSize的任务会放入任务队列中
        // 3.如果任务队列拒绝了，那么拒绝任务
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("aa");
            }
        });



        //创建一个缓存的数量个数线程的线程池，CachedThreadPool 用于并发执行大量短期的小任务

        // 1.coreSize为0,maxSize为无限大
        // 2.用的是传递队列SynchronousQueue，这个不保存任务，只是做传递，进队列会返回false
        // 3.因为没有核心线程 执行execute方法时，临时创建一个线程去执行任务，这个线程最大空闲时间keepAliveTime,超过会强制退出
        // 4.当短时间内任务太多，那么会创建很多线程

        ExecutorService executorService1 = Executors.newCachedThreadPool();

        executorService1.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("bb");
            }
        });

        Thread.sleep(10000);

        //创建只有一个线程数量的线程，这个线程也是核心线程
        // 1.coreSize和maxSize都是1
        // 2.会创建一个核心线程去执行任务，如果线程在忙，那么加入队列中
        // 3.因为是核心线程，默认情况是一直阻塞获取队列的任务，默认不存在超时的问题，并且因为只有一个线程，队列也是有序的，不存在并发，那么保证按照顺序执行

        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("cc");
            }
        });

        //创建一个定时执行一批任务的线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("dd");
            }
        },1, 2, TimeUnit.SECONDS);
    }

}
