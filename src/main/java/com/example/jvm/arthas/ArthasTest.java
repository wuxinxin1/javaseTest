package com.example.jvm.arthas;

import com.example.jvm.Hello;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ArthasTest {

    private Object o1=new Object();

    private Object o2=new Object();

    private ExecutorService executorService=Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws Exception {

        ArthasTest arthasTest = new ArthasTest();

        //arthasTest.cpuHigh();

        //arthasTest.deadLock();

        //arthasTest.park();

        //Hello hello = new Hello();

        //arthasTest.parkTime();

       /* UserService userService = new UserService();

        Thread.sleep(30000);

        userService.delUser(0);

        */

       //测试锁的情况

        /*for(int i=0;i<10;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    arthasTest.lock1();
                }
            });

            thread.setName("wxx"+i+"线程");

            thread.start();
        }*/

        ReentrantLock reentrantLock=new ReentrantLock();

        for(int i=0;i<10;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    arthasTest.lock1(reentrantLock);
                }
            });

            thread.setName("wxx"+i+"线程");

            thread.start();
        }

        System.in.read();
    }


    //cpu飙高
    private void cpuHigh(){

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName()+"cpu 100");
                }
            }
        });

    }

    //线程时间范围挂起---TIMED_WAI
    private void  parkTime(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.parkNanos(300000000000L);
                System.out.println("等待结束");
            }
        }).start();
    }


    //线程挂起---WAITING
    private void  park(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
            }
        }).start();
    }


    //等待锁状态---BLOCKED
    private void deadLock(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o1){
                    try {
                        System.out.println(Thread.currentThread().getName()+"获取o1锁");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (o2){
                        System.out.println(Thread.currentThread().getName()+"获取o2锁");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o2){
                    try {
                        System.out.println(Thread.currentThread().getName()+"获取o2锁");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (o1){
                        System.out.println(Thread.currentThread().getName()+"获取o1锁");
                    }
                }
            }
        }).start();
    }


    public synchronized void lock1(){

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public  void lock1(ReentrantLock reentrantLock){

        reentrantLock.lock();
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reentrantLock.unlock();
    }


}
