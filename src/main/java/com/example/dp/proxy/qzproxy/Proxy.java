package com.example.dp.proxy.qzproxy;

/**
 * @author wuxinxin
 * 代理类
 * 1.可以对被代理类做访问控制，可以在被代理执行前后做一些处理
 */
public class Proxy implements Subject {

    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        this.before();
        this.subject.request();
        this.after();
    }

    @Override
    public Subject getProxy() {
        return null;
    }

    private void after() {
        System.out.println("被代理执行前");
    }

    private void before() {
        System.out.println("被代理执行后");
    }


}
