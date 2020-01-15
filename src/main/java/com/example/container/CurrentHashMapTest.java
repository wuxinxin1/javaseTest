package com.example.container;

import java.util.concurrent.ConcurrentHashMap;

public class CurrentHashMapTest {

    public static void main(String[] args) {

        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

        for(int i=1;i<10;i++) {
            concurrentHashMap.put("aa", i);
        }

        concurrentHashMap.get("aa");


        //总是返回2的幂次方
        /*int n=4;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n);*/
    }

}
