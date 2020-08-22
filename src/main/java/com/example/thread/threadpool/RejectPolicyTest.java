package com.example.thread.threadpool;


import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试拒绝策略
 */
public class RejectPolicyTest {

    public static void main(String[] args) {

        LinkedBlockingQueue<Runnable> runnables = new LinkedBlockingQueue<>(10);


        //AbortPolicy拒绝策略
        /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5,
                0L, TimeUnit.MILLISECONDS,
                runnables);*/


        //DiscardPolicy拒绝策略
        /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5,
                0L, TimeUnit.MILLISECONDS,
                runnables, Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());*/


        //CallerRunsPolicy拒绝策略
        /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5,
                0L, TimeUnit.MILLISECONDS,
                runnables, Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());*/


        //DiscardOldestPolicy拒绝策略
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2,
                0L, TimeUnit.MILLISECONDS,
                runnables, Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());


        for(int i=0;i<20;i++){

            try {

                Object1 object1 = new Object1();

                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println();

                        System.out.print("当前处理线程:"+Thread.currentThread().getName()+"==");

                        System.out.print("当前剩余任务:"+runnables.size()+"==");

                        System.out.print("当前处理任务："+object1);

                        System.out.println();
                    }
                });

            }catch (Exception e){
                e.printStackTrace();
                System.out.println("任务"+(i)+"被拒绝");
            }

            System.out.println("添加任务"+i+"成功");
        }

        threadPoolExecutor.shutdown();

        System.out.println("线程池已经关闭");
    }

}

class Object1 {

    private static int j=0;

    private int i=0;

    public Object1() {
        this.i = j++;
    }

    @Override
    public String toString() {
        return "Object1{" +
                "i=" + i +
                '}';
    }
}
