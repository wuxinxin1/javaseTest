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

    private static CyclicBarrier cyclicBarrier=new CyclicBarrier(2);

    private static CyclicBarrier cyclicBarrier1=new CyclicBarrier(2,new A());

    public static void main(String[] args) {
        //test1();
        test2();
    }

    static class A implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println("执行优先任务开始");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("3333");
            System.out.println("执行优先任务结束");
        }
    }


    public static void test2(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("到达栅栏1");
                    cyclicBarrier1.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("1111");
            }
        }).start();

        try {
            System.out.println("到达栅栏2");
            cyclicBarrier1.await();
            System.out.println("4444");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("2222");
    }

    public static void test1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("1111");
                    cyclicBarrier.await();
                    System.out.println("2222");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            System.out.println("3333");
            cyclicBarrier.await();
            System.out.println("4444");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }


        System.out.println("fish");
    }

}
