package com.example.thread.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 lock+condition实现生产消费模式(等待/通知)
 *
 * lock和syn一样都能保证共享变量（同步代码）可见性
 */
public class ConditionTest {

    public static void main(String[] args) {

        Supermarket supermarket = new Supermarket();

        Producter producter = new Producter(supermarket);

        Consumer consumer = new Consumer(supermarket);

        Thread thread1 = new Thread(producter);

        Thread thread2= new Thread(consumer);


        thread1.start();
        thread2.start();
    }

}

class Producter implements Runnable{

    private Supermarket supermarket;

    public Producter(Supermarket supermarket) {
        this.supermarket = supermarket;
    }

     @Override
    public void run() {
         try {
             supermarket.p();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}

class Consumer implements Runnable{

    private Supermarket supermarket;

    public Consumer(Supermarket supermarket) {
        this.supermarket = supermarket;
    }

    @Override
    public void run() {
        try {
            supermarket.c();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Product{
    private int id;

    public int getId() {
        return id;
    }

    public Product(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                '}';
    }
}


class Supermarket{

    private ReentrantLock lock=new ReentrantLock();

    private Condition condition=lock.newCondition();

    private volatile List<Product> productList=new ArrayList<>(10);

    private static  int maxSize=9;

    public void p() throws Exception{
        lock.lock();

        for(int i=0;i<100;i++){

            if(productList.size()>maxSize){
                condition.signal();
                condition.await();
                i--;
            }else {
                Product product = new Product(i);
                productList.add(product);
                System.out.println("product============:"+product);
            }

        }
        //生产者最后完成生产任务，需要最后唤醒消费者去消费，把消费者线程转到阻塞队列中
        condition.signal();
        //生产者线程退出，唤醒后继节点
        lock.unlock();
        System.out.println("生产者退出");
    }

    public void c() throws Exception{
        lock.lock();
        while (true){
            if(productList.size()<=0){
                condition.signal();
                condition.await();
            }else {
                Product product = productList.get(productList.size() - 1);
                productList.remove(product);
                System.out.println("consumer:"+product);

                //如果阻塞队列已经没有线程，并且等待队列也没有线程，那么说明生产者已经退出，这是最后一批消费任务，执行完毕退出
                if(!lock.hasWaiters(condition) && !lock.hasQueuedThreads() && productList.size()==0){
                    break;
                }
            }
        }

        lock.unlock();
    }

}