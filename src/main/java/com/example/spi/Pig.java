package com.example.spi;

public class Pig implements Animal {

    @Override
    public void say() {
        System.out.println("猪叫");
    }
}
