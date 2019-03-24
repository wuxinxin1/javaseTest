package com.example;

/**
 * Created by Administrator on 2019/3/23/023.
 */
public class NativeTest {

    private native void sayHello();


    public static void main(String[] args) {
        new NativeTest().sayHello();
    }

}
