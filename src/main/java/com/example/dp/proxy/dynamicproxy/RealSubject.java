package com.example.dp.proxy.dynamicproxy;

/**
 * @author wuxinxin
 *
 * 被代理类--真正执行业务者
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("执行业务");
    }
}
