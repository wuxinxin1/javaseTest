package com.example.thread;

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
        for(int i=0;i<100;i++){
            try {
                supermarket.p(new Product(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        while (true) {
            try {
                supermarket.c();
            } catch (Exception e) {

            }
        }
    }
}


class Product{
    private int id;

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

/*class Supermarket{

    private Lock lock=new ReentrantLock();

    private Condition condition=lock.newCondition();

    private volatile List<Product> productList=new ArrayList<>(10);

    private static  int maxSize=10;

    public void p(Product product) throws Exception{
        lock.lock();
        ////唤醒condition上其他线程
        condition.signal();

        if(productList.size()<maxSize){
            productList.add(product);
            System.out.println("product:"+product);

        }else {
            //productList达到上限，释放锁,阻塞当前线程
            condition.await();
        }

        lock.unlock();
    }

    public void c() throws Exception{
        lock.lock();

        //唤醒condition上其他线程
        condition.signal();

        if(productList.size()>0){
            Product product = productList.get(productList.size() - 1);
            productList.remove(product);
            System.out.println("consum:--------"+product);
        }else{
            //productList没有可消费元素，释放锁,阻塞当前线程
            condition.await();
        }

        lock.unlock();
    }

}*/


class Supermarket{

    private volatile List<Product> productList=new ArrayList<>(10);

    private static  int maxSize=10;

    public synchronized void p(Product product) throws Exception{

        ////唤醒监视器上其他线程
        this.notify();

        if(productList.size()<maxSize){
            productList.add(product);
            System.out.println("product:"+product);

        }else {
            //productList达到上限，释放锁,阻塞当前线程
            this.wait();
        }

    }

    public synchronized void c() throws Exception{

        ////唤醒监视器上其他线程
        this.notify();

        if(productList.size()>0){
            Product product = productList.get(productList.size() - 1);
            productList.remove(product);
            System.out.println("consum:--------"+product);
        }else{
            //productList没有可消费元素，释放锁,阻塞当前线程
            this.wait();
        }
    }

}
