package com.example.container;

import java.util.HashMap;
import java.util.UUID;

public class HashMapTest {

    public static void main(String[] args) throws Exception{
        final HashMap<String, String> stringStringHashMap = new HashMap<>(2);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String s = UUID.randomUUID().toString();
                            stringStringHashMap.put(s, "");
                            System.out.println("put"+s);
                        }
                    },"wxx"+i).start();
                }
            }
        });

        thread.start();

        thread.join();
        System.out.println("执行完成");
    }

}
