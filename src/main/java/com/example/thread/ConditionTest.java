package com.example.thread;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Test3 test3 = new Test3();

        Producter producter = new Producter(test3);

        Consumer consumer = new Consumer(test3);

        Thread thread1 = new Thread(producter);

        Thread thread2= new Thread(consumer);


        thread1.start();
        thread2.start();

        /*BigDecimal bigDecimal = test2(new BigDecimal(100), 1);

        System.out.println(bigDecimal);*/

        //System.out.println(test1("abbcc"));

    }

    public static Map test1(String s){
        if(s==null && s.length()==0){
            throw new IllegalArgumentException();
        }

        s=s.toLowerCase();
        Map<Character,Integer> map=new HashMap();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }
        return map;

    }

    public static BigDecimal test2(BigDecimal amount, int count){
        if (amount==null || amount.compareTo(BigDecimal.ZERO)<=0 || count<1){
            throw new IllegalArgumentException();
        }
        return amount.divide(new BigDecimal(count),2, BigDecimal.ROUND_HALF_UP);
    }

}

class Producter implements Runnable{

    /*private Supermarket supermarket;

    public Producter(Supermarket supermarket) {
        this.supermarket = supermarket;
    }*/
    private Test3 test3;

    public Producter(Test3 test3) {
        this.test3 = test3;
    }

    /* @Override
    public void run() {
        for(int i=0;i<100;i++){
            try {
                supermarket.p(new Product(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

     @Override
    public void run() {

        for(int i=0;i<100;i++){
            try {
                test3.p();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{

   /* private Supermarket supermarket;

    public Consumer(Supermarket supermarket) {
        this.supermarket = supermarket;
    }*/

    private Test3 test3;

    public Consumer(Test3 test3) {
        this.test3 = test3;
    }

    @Override
    public void run() {
        while (true) {
            try {
                test3.c();
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


class Supermarket{

    private Lock lock=new ReentrantLock();

    private Condition condition=lock.newCondition();

    private volatile List<Product> productList=new ArrayList<>(10);

    private static  int maxSize=10;

    public void p(Product product) throws Exception{
        lock.lock();

        if(productList.size()<maxSize){
            productList.add(product);
            System.out.println("product:"+product);

        }else {
            //唤醒condition上其他线程
            condition.signal();
            //productList达到上限，释放锁,阻塞当前线程
            condition.await();
        }

        lock.unlock();
    }

    public void c() throws Exception{
        lock.lock();

        if(productList.size()>0){
            Product product = productList.get(productList.size() - 1);
            productList.remove(product);
            System.out.println("consum:--------"+product);
        }else{
            //唤醒condition上其他线程
            condition.signal();
            //productList没有可消费元素，释放锁,阻塞当前线程
            condition.await();
        }

        lock.unlock();
    }

}

/*
class Supermarket{

    private Lock lock=new ReentrantLock();

    private Condition condition=lock.newCondition();

    private volatile List<Product> productList=new ArrayList<>(10);

    private static  int maxSize=10;

    public void p(Product product) throws Exception{
        lock.lock();

        if(productList.size()<maxSize){
            productList.add(product);
            System.out.println("product:"+product);

        }else {
            //唤醒condition上其他线程
            condition.signal();
            //productList达到上限，释放锁,阻塞当前线程
            condition.await();
        }

        lock.unlock();
    }

    public void c() throws Exception{
        lock.lock();

        if(productList.size()>0){
            Product product = productList.get(productList.size() - 1);
            productList.remove(product);
            System.out.println("consum:--------"+product);
        }else{
            //唤醒condition上其他线程
            condition.signal();
            //productList没有可消费元素，释放锁,阻塞当前线程
            condition.await();
        }

        lock.unlock();
    }

}
*/


/*class Supermarket{

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

}*/

class Test3{

    private Lock lock=new ReentrantLock();

    private Condition condition=lock.newCondition();

    //private volatile boolean flag=true;
    private  boolean flag=true;
    //private volatile Integer a=0;
    private  Integer a=0;

    public void p() throws InterruptedException {

        lock.lock();

        if(flag){
            a++;
            flag=false;
            System.out.println(a);
        }else{
            condition.signal();
            condition.await();
        }

        lock.unlock();
    }

    public void c() throws InterruptedException {
        lock.lock();

        if(!flag){
            a++;
            flag=true;
            System.out.println(a);
        }else{
            condition.signal();
            condition.await();
        }

        lock.unlock();
    }
}
