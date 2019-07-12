package com.example.guava;

import com.google.common.base.Optional;

/**
 * @author: wuxinxin
 * @version: v1.0
 * @description: com.example.guava
 * @date:2019/5/31
 */
public class TestOptional {
    public static void main(String[] args) {

        test01();
    }

    public static void   test01(){
        Integer a=null;
        Integer b=10;
        //Optional<Integer> integerOptional = Optional.of(a);
        Optional<Integer> integerOptional1 = Optional.of(b);
        System.out.println(integerOptional1.get());
        System.out.println(integerOptional1.orNull());
    }
}
