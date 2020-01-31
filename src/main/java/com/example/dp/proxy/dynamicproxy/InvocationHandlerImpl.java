package com.example.dp.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wuxinxin
 */
public class InvocationHandlerImpl implements InvocationHandler {

    /**
     * 被代理实例
     */
    private Object object;

    /**
     * 初始化被代理对象
     * @param object
     */
    public InvocationHandlerImpl(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //连接点放入通知
        new BeforeAdvice().before();

        method.invoke(this.object,args);
        return proxy;
    }
}
