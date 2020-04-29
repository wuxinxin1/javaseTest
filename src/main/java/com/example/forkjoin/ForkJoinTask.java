package com.example.forkjoin;


import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * forkjoin的测试用例
 */
public class ForkJoinTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        System.out.println("开始时间:"+new Date());
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Long integer = forkJoinPool.submit(new Task(1L, 100000L)).get();

        System.out.println("计算结果:"+integer);
        System.out.println("开始时间:"+new Date());

        System.out.println("开始时间2:"+new Date());
        Long sum=0L;
        Long i=1L;
        while (i<=100000L){
            sum+=i;
            i++;
            Thread.sleep(2);
        }
        System.out.println(sum);
        System.out.println("开始时间2:"+new Date());
    }

}


class Task extends RecursiveTask<Long>{

    private Long start;

    private Long end;

    public Task(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        Long sum=0L;

        boolean canCompute=(end-start)<=2;

        if(canCompute){

            for(Long i=start;i<=end;i++){
                sum+=i;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName()+"=="+sum);

        }else {

            Long middle=(start+end)/2;

            Task task = new Task(start, middle);

            Task task1 = new Task(middle + 1, end);

            task.fork();
            task1.fork();

            Long join = task.join();
            Long join1 = task1.join();

            sum=join+join1;

        }

        return sum;
    }
}
