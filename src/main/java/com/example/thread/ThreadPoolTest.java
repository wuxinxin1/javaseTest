package com.example.thread;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPoolTest {

    private static int core=0;

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0;i<10000;i++) {
            executorService.execute(new H(i));
        }

        executorService.shutdown();
        System.out.println("任务分配完毕");

        //测试try finally 这个适合不处理异常，直接终止程序
        /*try {
            int i=1/0;
        }finally {
            System.out.println("finally处理");
        }*/
        /*try {
            int i=1/0;
        }catch (Exception e){
            System.out.println("异常");
        }finally{
            System.out.println("finally处理");
        }
        System.out.println("程序终止了吗？");*/

//测试线程池中用到的队列

        //poll和take的区别  poll有就取出来，没有就返回null  take是阻塞的，一直会等待
        /*LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(3);

        System.out.println(linkedBlockingQueue.offer(1));
        System.out.println(linkedBlockingQueue.offer(2));
        System.out.println(linkedBlockingQueue.offer(3));
        System.out.println(linkedBlockingQueue.offer(4));
        System.out.println(linkedBlockingQueue.offer(5));
        System.out.println(linkedBlockingQueue.offer(6));

        Object poll = linkedBlockingQueue.poll();
        Object poll1 = linkedBlockingQueue.poll();
        Object take = linkedBlockingQueue.take();
        Object poll2 = linkedBlockingQueue.poll();

        System.out.println(poll);
        System.out.println(poll1);
        System.out.println(take);
        System.out.println(poll2);*/
/*
        Iterator iterator = linkedBlockingQueue.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }

*/
        //校验for retry语法,break和continue+retry用来指定终止哪一层或者继续在哪一层执行
        /*retry:
        for(;;){
            System.out.println("w");

            for (;;){
               if(core<5){
                   core++;
                   System.out.println("当前core="+core);
                   break retry;
               }
                System.out.println("n");
               continue retry;
            }
        }*/

        //-536870912
        //System.out.println(RUNNING);
        //0
        //System.out.println(SHUTDOWN);
    }


}

class H implements Runnable {

    private int id;

    public H(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+"任务"+id);
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
