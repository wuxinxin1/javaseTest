package com.example.interview;


import java.util.*;

/**
 * 测试 List,Set,Map特性
 */
public class Test3 {

    public static void main(String[] args) {

        List<Object> objects = new ArrayList<>();

        objects.add(null);
        objects.add(null);
        System.out.println(objects.size());

        HashSet<Object> objects1 = new HashSet<>();
        objects1.add(null);
        objects.add(null);
        System.out.println(objects1.size());

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        objectObjectHashMap.put(null,1);
        objectObjectHashMap.put(null,2);

        System.out.println(objectObjectHashMap.size());

        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        objectObjectHashtable.put(null,1);

    }

}
