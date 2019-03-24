package com.example.thread;

/**
 * 传统线程通信的方式，使用同步对象的wait(),notify(),notifyAll()
 * 实验要求，存三次，取三次
 * 1.wait()会导致当前线程暂停执行，使当前线程在当前同步器上等待，直到被同一个同步器唤醒（notify）,b被唤醒后继续接着上次地方开始执行
 * 2.notify(),会唤醒当前同步器上的其它线程继续执行(需要释放当前同步器的锁才会执行)
 * Created by Administrator on 2019/3/24/024.
 */
public class WaitNotifyNotifyAllTest {
    public static void main(String[] args) {

        AccountA accountA = new AccountA();

        DrawThreadA drawThreadA=new DrawThreadA(accountA);

        DepositThreadA depositThreadA = new DepositThreadA(accountA);


        new Thread(drawThreadA).start();
        new Thread(depositThreadA).start();


    }
}

class DrawThreadA implements Runnable{

    private AccountA accountA;

    public DrawThreadA(AccountA accountA) {
        this.accountA = accountA;
    }

    @Override
    public void run() {
        while (true) {
            accountA.draw();
        }
    }
}

class DepositThreadA implements Runnable{

    private AccountA accountA;

    public DepositThreadA(AccountA accountA) {
        this.accountA = accountA;
    }

    @Override
    public void run() {
        while (true) {
            accountA.deposit();
        }
    }
}

class AccountA{
    private int balance;

    public AccountA() {
    }

    public  void draw(){
        System.out.println("*********************");
        synchronized(this) {
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
                    notifyAll();//唤醒当前同步器上其他线程，到这一步，被唤醒的线程还无法执行，因为锁还未释放
                                // (仅仅进行唤醒，被唤醒的线程是不会执行的，只有释放锁(wait())同时其他线程才开始执行)
                    Thread.sleep(2000);
                    //发生异常,当前线程将不会等待，只会释放锁
                    //int i=10/0;
                    System.out.println("取钱业务终止");
                    wait();//停止执行，在当前同步器上等待，释放同步器的锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    //注意：如果在wait前发生异常，将不会满足存三次取三次的业务需求
                    System.out.println("在调用wait方法前发生异常:"+e);
                    try {
                        wait();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    public  void deposit(){
        System.out.println("----------------------");
        synchronized (this) {
            if (balance < 300) {
                System.out.println("存入100元");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance += 100;

            } else {
                try {
                    notifyAll(); //唤醒当前同步器上其他线程，到这一步，被唤醒的线程还无法执行，因为锁还未释放
                    // (仅仅进行唤醒，被唤醒的线程是不会执行的，只有释放锁(wait())同时其他线程才开始执行)
                    Thread.sleep(2000);
                    System.out.println("存钱业务终止");
                    wait();  //停止执行，在当前同步器上等待，释放同步器的锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/*class AccountA{
    private int balance;

    public AccountA() {
    }

    public synchronized void draw(){
        System.out.println("*********************");
        if(balance>0){
            System.out.println("取出100元");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance-=100;

        }else {
            try {
                notify();//唤醒当前同步器上其他线程，到这一步，被唤醒的线程还无法执行，因为锁还未释放
                System.out.println("取钱业务终止");
                wait();//停止执行，在当前同步器上等待，释放同步器的锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void deposit(){
        System.out.println("----------------------");
        if(balance<300){
            System.out.println("存入100元");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance+=100;

        }else {
            try {
                notify(); //唤醒当前同步器上其他线程，到这一步，被唤醒的线程还无法执行，因为锁还未释放
                System.out.println("存钱业务终止");
                wait();  //停止执行，在当前同步器上等待，释放同步器的锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}*/

/*class AccountA{
    private boolean hasBalance;

    public AccountA() {
    }

    public synchronized void draw(){
        System.out.println("*********************");
        if(hasBalance){
            System.out.println("取出100元");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hasBalance=false;

        }else {
            try {
                notify();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void deposit(){
        System.out.println("----------------------");
        if(!hasBalance){
            System.out.println("存入100元");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hasBalance=true;

        }else {
            try {
                notify();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}*/
