package com.example.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现多线程的第三种方式 实现Callable
 *
 * 1.通过泛型可以指明返回值
 * 2.可以申明抛出异常
 *
 * Created by Administrator on 2019/3/24/024.
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task task=new Task();
        FutureTask<Integer> futureTask=new FutureTask(task);

        Thread thread = new Thread(futureTask);

        thread.start();

        System.out.println("-------获取执行结果开始------");
        //获取线程返回值
        Integer integer = futureTask.get();
        System.out.println("-------获取执行结果结束------");

        System.out.println(integer);

    }
}

class Task implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        for(int i=0;i<10;i++){
            Thread.sleep(1000);
            System.out.println("睡眠"+i+"次");
        }
        System.out.println("线程执行完毕");
        return 10;
    }
}
