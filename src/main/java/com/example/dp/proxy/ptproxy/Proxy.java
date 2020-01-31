package com.example.dp.proxy.ptproxy;

/**
 * @author wuxinxin
 * 代理类
 * 1.可以对被代理类做访问控制，可以在被代理执行前后做一些处理
 * 2.代理类主要功能是为了增强，所有难免要实现一些增强的接口
 */
public class Proxy implements Subject ,Up1 {

    private Subject subject;

    public Proxy() {
        this.subject = new RealSubject();
    }

    @Override
    public void request() {
        this.before();
        this.subject.request();
        this.after();

        //增强
        this.up();

    }

    private void after() {
        System.out.println("被代理执行前");
    }

    private void before() {
        System.out.println("被代理执行后");
    }


    /**
     * 增强1
     */
    @Override
    public void up() {
        System.out.println("增强1");
    }
}
