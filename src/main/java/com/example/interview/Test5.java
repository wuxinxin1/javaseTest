package com.example.interview;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 所有任务完成，然后统计结果
 */
public class Test5 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CountDownLatch countDownLatch = new CountDownLatch(5);

        List<FutureTask> futureTaskList=new ArrayList<>();

        for(int i=1;i<=100;i++) {
            FutureTask<Integer> integerFutureTask = new FutureTask<Integer>(new Task(i,countDownLatch));
            futureTaskList.add(integerFutureTask);
            new Thread(integerFutureTask).start();
        }

        countDownLatch.await();

        int count=0;

        for (FutureTask<Integer> futureTask: futureTaskList
             ) {
            count+=futureTask.get();
        }

        System.out.println("统计:"+count);
    }

}

class Task implements Callable<Integer> {

    private Integer integer;

    private CountDownLatch countDownLatch;

    public Task(Integer integer) {
        this.integer = integer;
    }

    public Task(Integer integer, CountDownLatch countDownLatch) {
        this.integer = integer;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public Integer call() throws Exception {
        countDownLatch.countDown();
        return integer;
    }
}
