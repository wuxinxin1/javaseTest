package com.example.redis;

import io.netty.util.internal.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

/**
 * redis 实现分布式锁的解决方案
 * 1.自动续期
 * 2.线程自动缓存
 */
public class RedissionTest {

    private static RedissonClient redisson;

   static {
       Config config = new Config();
       //config.setTransportMode(TransportMode.EPOLL);

       config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redisson = Redisson.create(config);
   }


    public static void main(String[] args) {

        RLock wxx = redisson.getLock("wxx");

        wxx.lock();

        //wxx.unlock();
    }
}
