package com.example.interview;

/**
 * 值传递和引用传递
 */
public class Test1 {

    public static void main(String[] args) {

        User user = new User(1,"wxx");

        int a=1;

        Test1 test1 = new Test1();

        test1.method1(user,a);

        System.out.println(user+"==="+a);

    }

    public void  method1(User s,int a){

        /*s.setId(2);

        a=10;*/

        s=new User(2,"liujing");

        a=10;

        System.out.println(s+"=="+a);

    }



}
