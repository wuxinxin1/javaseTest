package com.example.nio.netty.mainsub;

import java.io.IOException;

/**
 * 主从Reactor单线程模型
 */
public class MainSubReactorModelTest {

    public static void main(String[] args) throws IOException {
        MainSubReactorModel mainSubReactorModel = new MainSubReactorModel(6666);
        mainSubReactorModel.start();
    }

}
