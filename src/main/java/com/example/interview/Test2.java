package com.example.interview;


import java.util.*;

/**
 * 测试 hashCode和equals方法
 *
 *  目的：是为了在使用hash相关集合的时候，唯一标识一个对象
 *
 *  1.在HashSet中，区分两个对象是否相等，要判断hashCode和equals都返回true
 *  2.在HashMap也是一样
 *
 */
class Test2 {

    public static void main(String[] args) {

        HashSet<Integer> hashSet = new HashSet<>();

        System.out.println(hashSet.add(1));
        System.out.println(hashSet.add(1));

        System.out.println(hashSet.size());


        HashSet<User> users = new HashSet<>();

        System.out.println(users.add(new User(1, "wxx")));
        System.out.println(users.add(new User(1, "wxx")));
        System.out.println(users.add(new User(1, "wxx")));
        System.out.println(users.add(new User(1, "wxx")));
        System.out.println(users.add(new User(2, "wxx")));

        System.out.println(users.size());


        System.out.println(new User(1, "wxx").hashCode());
        System.out.println(new User(1, "wxx").hashCode());

        //String;
        //Integer
        //Map, Collection, Set,List,Queue
    }

}
