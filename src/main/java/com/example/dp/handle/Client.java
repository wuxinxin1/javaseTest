package com.example.dp.handle;

/**
 * 测试责任链模式
 *
 * 1.责任链模式的核心就是提供一个统一的入口，具体怎么处理，哪个节点处理用户不需要关心，这样有利于业务扩展
 * 2.责任链中的节点不宜过多，会影响系统性能，应该在设置下一个节点的时候进行限制，当到达一个阈值拒绝添加节点
 */
public class Client {

    public static void main(String[] args) {

        Request request = new Request(3);

        //责任链模式总是从一个固定的处理器开始
        Handle handle = new HandleImpl1();
        HandleImpl2 handleImpl2 = new HandleImpl2();
        handle.setNextHandle(handleImpl2);
        HandleImpl3 handleImpl3 = new HandleImpl3();
        handleImpl2.setNextHandle(handleImpl3);

        //开始处理
        handle.handleMessage(request);


        /**
         * 直接封装一个责任链上下文，这样更有利于解耦
         */
        HandleContext.processor(request);
    }

}
