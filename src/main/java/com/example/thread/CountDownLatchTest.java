package com.example.thread;
import java.util.concurrent.CountDownLatch;

/**
 * @author wuxinxin
 *
 * CountDownLatch 可以设置一个整形值，当执行countDownLatch.countDown()数量到达这个整形值，才会往下执行
 * 否则countDownLatch.await()将会一直阻塞
 */
public class CountDownLatchTest {

    static CountDownLatch countDownLatch=new CountDownLatch(2);

    public static void main(String[] args) throws Exception{
        //test1();

        test2();
    }


    public static void test1() throws Exception{

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("fish-----1");
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("fish-----2");
            }
        });

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();

        System.out.println("fish");
    }

    public static void test2() throws Exception{

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("fish-----1");
                    //Thread.sleep(1000);
                    countDownLatch.countDown();
                    Thread.sleep(1000);
                    System.out.println("fish-----11");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("fish-----2");
                    Thread.sleep(2000);
                    countDownLatch.countDown();
                    Thread.sleep(1000);
                    System.out.println("fish-----22");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread1.start();

        countDownLatch.await();

        System.out.println("fish");
    }
}
