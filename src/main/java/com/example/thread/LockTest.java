package com.example.thread;

import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2019/3/24/024.
 */
public class LockTest {

    public static void main(String[] args) {
        Account account = new Account("111111", 190);

        DrawThread drawThread1= new DrawThread("甲",account);
        DrawThread drawThread2= new DrawThread("乙",account);
        DrawThread drawThread3= new DrawThread("丙",account);

        new Thread(drawThread1).start();
        new Thread(drawThread3).start();
        new Thread(drawThread2).start();
    }

}

class DrawThread implements Runnable{

    private Account account;
    private String name;

    public DrawThread(String name,Account account) {
        this.account = account;
        this.name=name;
    }

    @Override
    public void run() {
        account.draw();
    }
}

class Account{

    private String accountNo;
    private int balance;

    //定义锁，不使用synchronized
    private final ReentrantLock lock=new ReentrantLock();

    public Account(String accountNo, int balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    //public synchronized void draw(){
    /*public  void draw(){
        if(100<=balance){
            System.out.println(Thread.currentThread().getName()+"---取钱---"+100);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        balance-=100;
        System.out.println("账户余额为:"+balance);

        }else {
            System.out.println("余额不足取钱失败,当前余额为:"+balance);
        }
    }*/

    public  void draw(){
        //加锁
        lock.lock();
        try {
            if(100<=balance){
                System.out.println(Thread.currentThread().getName()+"---取钱---"+100);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                balance-=100;
                System.out.println("账户余额为:"+balance);

            }else {
                System.out.println("余额不足取钱失败,当前余额为:"+balance);
            }
        }finally {
            lock.unlock();
        }
    }
}
