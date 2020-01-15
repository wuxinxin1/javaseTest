package com.example.container;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wuxinxin
 */
public class CopyOnWriteArrayListTest {

    private Object[] objects={new Object()};

    public static void main(String[] args) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

        copyOnWriteArrayList.add(1);
        copyOnWriteArrayList.remove(1);
        copyOnWriteArrayList.get(1);

        //测试引用
        CopyOnWriteArrayListTest copyOnWriteArrayListTest = new CopyOnWriteArrayListTest();
        t1(copyOnWriteArrayListTest.objects);
    }


    public static void t1(Object[] objects){
        System.out.println(objects);
    }

}
