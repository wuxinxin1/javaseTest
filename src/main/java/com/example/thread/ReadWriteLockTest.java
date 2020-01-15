package com.example.thread;

import org.apache.kafka.common.protocol.types.Field;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wuxinxin
 * 读写锁测试
 *
 */
public class ReadWriteLockTest {

    private static ReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    private static HashMap<String,Object> hashMap=new HashMap<>();

    static {
        hashMap.put("aa","vaa");
    }

    public static void main(String[] args) {
        test1();
        //test1();
    }


    public static void read(String key){
        Lock lock = readWriteLock.readLock();
        lock.lock();
        System.out.println("当前线程"+Thread.currentThread().getName()+hashMap.get(key));
        lock.unlock();
    }


    public static void write(String key, String value){
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        hashMap.put(key,value);
        lock.unlock();
    }

    /**
     * 简单使用读写锁
     */
    public static void test1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++) {
                    read("aa");
                }
            }
        }).start();

    }

}
