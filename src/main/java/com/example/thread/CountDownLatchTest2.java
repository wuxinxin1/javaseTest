package com.example.thread;
import java.util.concurrent.CountDownLatch;

/**
 * @author wuxinxin
 *
 * CountDownLatch 可以设置一个整形值，当执行countDownLatch.countDown()数量到达这个整形值，才会往下执行
 * 否则countDownLatch.await()将会一直阻塞
 */
public class CountDownLatchTest2 {

    public static void main(String[] args) throws Exception{


        int n=5;

        CountDownLatch countDownLatch = new CountDownLatch(n);

        /*for(int i=0;i<n;i++){
            new Thread(new Work(i,countDownLatch)).start();
        }*/

        new Thread(new Work(n,countDownLatch)).start();


        countDownLatch.await();




        System.out.println("所有任务完成");
    }

}

/*class Work implements Runnable{

    private int i;

    private CountDownLatch countDownLatch;

    public Work(int i, CountDownLatch countDownLatch) {
        this.i = i;
        this.countDownLatch = countDownLatch;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(i*1000);
            System.out.println("任务"+i+"完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }
}*/


class Work implements Runnable{

    private int i;

    private CountDownLatch countDownLatch;

    public Work(int i, CountDownLatch countDownLatch) {
        this.i = i;
        this.countDownLatch = countDownLatch;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            for(int j=0;j<i;j++) {
                System.out.println("唤醒:"+(j+1)+"次");
                countDownLatch.countDown();
            }
        }
    }
}
