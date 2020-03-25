package com.example.others;


import org.apache.kafka.common.protocol.types.Field;

import java.util.HashMap;
import java.util.Map;

/**
 * lamd表达式的参数测试，其实也就是局部内部类的测试
 */
public class LamTest {

    private static Map<String, BeanFactory> map=new HashMap<>();


    public static void main(String[] args) {

        String s1="hello";
        Integer i1=10;
    }

    public static void pushData(String key,BeanFactory beanFactory){
        map.put(key,beanFactory);
    }

}


interface BeanFactory{
    void getObject();
}