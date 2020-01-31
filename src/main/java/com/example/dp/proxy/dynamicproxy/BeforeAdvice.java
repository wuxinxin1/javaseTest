package com.example.dp.proxy.dynamicproxy;

public class BeforeAdvice implements IAdvice {
    @Override
    public void before() {
        System.out.println("前置通知");
    }
}
