package com.example.thread;


import java.util.concurrent.locks.LockSupport;

/**
 * 用于阻塞和唤醒一个线程的工具
 * @author wuxinxin
 */
public class LockSupportTest {

    public static void main(String[] args) {

        Test test = new Test();

        Thread thread1 = new Thread(test);
        Thread thread2 = new Thread(test);

        test.setThread1(thread1);
        test.setThread2(thread2);

        thread1.start();
        thread2.start();

    }

}

class Test implements Runnable{

    private Thread thread1;

    private Thread thread2;

    public Thread getThread1() {
        return thread1;
    }

    public void setThread1(Thread thread1) {
        this.thread1 = thread1;
    }

    public Thread getThread2() {
        return thread2;
    }

    public void setThread2(Thread thread2) {
        this.thread2 = thread2;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
            if(Thread.currentThread()==thread1){
                LockSupport.unpark(thread2);
            }else {
                LockSupport.unpark(thread1);
            }

            if(i!=9) {
                LockSupport.park(Thread.currentThread()==thread1?thread2:thread1);
            }
        }
    }
}
