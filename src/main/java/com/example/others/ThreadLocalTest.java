package com.example.others;

import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;

/**
 *对于ThreadLocal的研究
 * Created by Administrator on 2019/4/10/010.
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        Bank bank1 = new Bank();
        Bank bank2 = new Bank();
        Bank bank3 = new Bank();
        /*bank.set();
        System.out.println(Thread.currentThread().getName()+":"+bank.get());*/
        runner runner = new runner(bank1,bank2,bank3);

        Thread t1 = new Thread(runner);
        //Thread t2 = new Thread(runner);

        t1.start();
        //t2.start();
    }

    @Test
    public void test01(){
        int HASH_INCREMENT = 0x61c88647;
        System.out.println(HASH_INCREMENT &15);//1640531527
        System.out.println(HASH_INCREMENT*2 &15);//-1013904242
        System.out.println(HASH_INCREMENT*3 &15);//626627285
        System.out.println(HASH_INCREMENT*4 &15);//-2027808484
        System.out.println(HASH_INCREMENT*5 &15);//-387276957
        System.out.println(HASH_INCREMENT*6 &15);//1253254570

        System.out.println(HASH_INCREMENT*7 &15);//-1013904242
        System.out.println(HASH_INCREMENT*8 &15);//626627285
        System.out.println(HASH_INCREMENT*9 &15);//-2027808484
        System.out.println(HASH_INCREMENT*10 &15);//-387276957
        System.out.println(HASH_INCREMENT*11 &15);//1253254570


        System.out.println(HASH_INCREMENT*12 &15);//-1013904242
        System.out.println(HASH_INCREMENT*13 &15);//626627285
        System.out.println(HASH_INCREMENT*14 &15);//-2027808484
        System.out.println(HASH_INCREMENT*15 &15);//-387276957
        System.out.println(HASH_INCREMENT*16 &15);//1253254570

        System.out.println(HASH_INCREMENT*17 &15);//1253254570
    }
}

class Bank{
    private ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){
       @Override
        protected Integer initialValue() {
            //System.out.println("init");
            return 0;
        }
    };
    public int get(){
        Integer a=threadLocal.get();
        return a;
    }

    public void set(int i){
        threadLocal.set(i);
    }
}

class runner implements Runnable{

    private Bank bank1;
    private Bank bank2;
    private Bank bank3;

    public runner(Bank bank1,Bank bank2,Bank bank3) {
        this.bank1 = bank1;
        this.bank2=bank2;
        this.bank3=bank3;
    }

    @Override
    public void run() {
        //for(int i=0;i<1;i++){
            /*bank.set();
            System.out.println(Thread.currentThread().getName()+":"+bank.get());*/
            bank1.set(1);
            bank2.set(2);
            bank3.set(3);

            bank2.get();
        //}
    }
}

