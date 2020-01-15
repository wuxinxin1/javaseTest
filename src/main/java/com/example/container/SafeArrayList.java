package com.example.container;

import org.apache.kafka.common.utils.CopyOnWriteMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 列举线程安全的List
 * 1.vector
 * 2.Collections工具类作为代理，对读写方法加synchronized
 * 3.CopyOnWriteArrayList,对读写方法加lock
 */
public class SafeArrayList {

    public static void main(String[] args) {
        //1.vector和ArrayList实现基本一样，但是在读写加了synchronized
        Vector<Integer> integers = new Vector<>();
        integers.get(1);
        //2.Collections.synchronizedList做转换，Collections里面有SynchronizedList实现，这个实现就是个代理对读写方法加了个synchronized
        //  hashMap,list,set转换原理一样
        List<Integer> integers1 = Collections.synchronizedList(new ArrayList<Integer>());
        Map<String, Integer> stringIntegerMap = Collections.synchronizedMap(new HashMap<String, Integer>());
        integers1.add(1);
        integers.get(0);
        //3.CopyOnWriteArrayList方式使用lock去进行同步，和vector有点像，只不过用的锁的方式不一样
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        CopyOnWriteArraySet<Object> objects = new CopyOnWriteArraySet<>();
        new ConcurrentHashMap<>();
        objects.add(1);
        copyOnWriteArrayList.add(1);
    }

}
