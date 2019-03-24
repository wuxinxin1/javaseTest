package com.example.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java 1.5之后推出的线程通信方式
 * Created by Administrator on 2019/3/24/024.
 */
public class CoditionTest {

    public static void main(String[] args) {

        AccountB accountB = new AccountB();

        DrawThreadB drawThreadB=new DrawThreadB(accountB);

        DepositThreadB depositThreadB = new DepositThreadB(accountB);


        new Thread(drawThreadB).start();
        new Thread(depositThreadB).start();


    }
}
class DrawThreadB implements Runnable{

    private AccountB accountB;

    public DrawThreadB(AccountB accountB) {
        this.accountB = accountB;
    }

    @Override
    public void run() {
        while (true) {
            accountB.draw();
        }
    }
}

class DepositThreadB implements Runnable{

    private AccountB accountB;

    public DepositThreadB(AccountB accountB) {
        this.accountB = accountB;
    }

    @Override
    public void run() {
        while (true) {
            accountB.deposit();
        }
    }
}

class AccountB{
    private int balance;

    private  Lock reentrantLock=new ReentrantLock();

    private  Condition condition=reentrantLock.newCondition();

    public AccountB() {
    }

    public  void draw()  {
        System.out.println("*********************");

        reentrantLock.lock();
        try {
            if (balance > 0) {
                System.out.println("取出100元");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= 100;

            } else {
                try {
                    condition.signal();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            reentrantLock.unlock();
        }

    }

    public  void deposit(){
        System.out.println("----------------------");
        reentrantLock.lock();
        try {
            if (balance < 500) {
                System.out.println("存入100元");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance += 100;
            } else {
                try {
                    condition.signal();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            reentrantLock.unlock();
        }
    }
}
