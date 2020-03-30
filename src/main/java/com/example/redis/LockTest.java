/*
package com.example.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Date;


//redis锁问题
// 1. A线程还没执行完，但是时间已经过期了，B线程还是可以并发执行（过期时间30s,业务执行50s,导致AB都获取到锁,然后A线程误把B的锁解了.....）

class LockTest2 {

    public static void main(String[] args) throws Exception{

        RedisLock redisLock = new RedisLock();

        while (!redisLock.lock()){

        }

        System.out.println("加锁成功执行业务"+new Date());
        Thread.sleep(50000);

        redisLock.unLock();

    }

}


class RedisLock{

    private static Jedis jedis=new Jedis("localhost");

    private static final String xxLock="xxLock";

    private static final String xxLockValue="xxLockValue";

    private static final Integer xxLockSeconds=30;
    */
/**
     * 加锁
     *//*

    public Boolean lock(){
        SetParams setParams = new SetParams();
        setParams.ex(xxLockSeconds).nx();
        return jedis.set(xxLock, xxLockValue, setParams)!=null;
    }

    */
/**
     * 释放锁
     *//*

    public void unLock(){
        jedis.del(xxLock);
    }

}*/
