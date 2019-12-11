package com.example.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wuxinxin
 */
public class LockTest3 {

    private static ReentrantLock2 reentrantLock1=new ReentrantLock2(true);

    private static ReentrantLock2 reentrantLock2=new ReentrantLock2(false);



    public static void main(String[] args) {

        //t1();
        t2();
    }

    public static void t1(){

        for (int i=0;i<10;i++){
            new ThreadTest(reentrantLock1,i).start();
        }
    }

    public static void t2(){

        for (int i=0;i<10;i++){
            new ThreadTest(reentrantLock2,i).start();
        }
    }

    static class ThreadTest extends Thread{

        private ReentrantLock2 lock;
        private int i;

        private ThreadTest(ReentrantLock2 lock,int i){
            this.lock=lock;
            this.i=i;
        }

        @Override
        public void run(){
            lock.lock();
            try {
                //Thread.sleep(1000);
                System.out.println("当前线程:"+Thread.currentThread()+"i="+i+"\t\t\t\t队列线程:"+lock.getQueuedThreads());
            }catch (Exception e){

            }

            lock.unlock();
        }
    }

}

class ReentrantLock2 extends ReentrantLock{

    public ReentrantLock2(boolean fair) {
        super(fair);
    }

    @Override
    public ArrayList<Thread> getQueuedThreads(){
        ArrayList<Thread> queuedThreads = new ArrayList<>(super.getQueuedThreads());
        Collections.reverse(queuedThreads);
        return queuedThreads;
    }
}
