package com.example.spi;

public class Dog implements Animal {
    @Override
    public void say() {
        System.out.println("狗叫");
    }
}
