package com.example.nio.netty;

import java.io.IOException;

/**
 * 测试单Reactor单线程模型
 * @author wuxinxin
 */
public class ReactorModel1Test {

    public static void main(String[] args) throws IOException {

        ReactorModel1 reactorTest1 = new ReactorModel1(6666);

        reactorTest1.start();

        System.in.read();

    }
}
