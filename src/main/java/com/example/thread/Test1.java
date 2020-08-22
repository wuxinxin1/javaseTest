package com.example.thread;

/**
 * @author wuxinxin
 */
public class Test1 {

    private Test1 test1=this;

    public static void main(String[] args) {

        Test1 test1 = new Test1();

        System.out.println(test1);

        System.out.println(test1.test1);

    }
}
