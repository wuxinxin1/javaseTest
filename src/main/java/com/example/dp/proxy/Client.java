package com.example.dp.proxy;

import com.example.dp.proxy.dynamicproxy.DynamicProxy;
import com.example.dp.proxy.dynamicproxy.InvocationHandlerImpl;
import com.example.dp.proxy.dynamicproxy.RealSubject;
import com.example.dp.proxy.dynamicproxy.Subject;
import com.example.dp.proxy.ptproxy.Proxy;

/**
 * @author wuxinxin
 *
 * 测试代理模式
 *
 */

public class Client {

    public static void main(String[] args) {

        //测试静态代理
        //1.测试普通代理，不需要知道真实实现者的存在，客户端只和代理打交道，这样真正实现和客户端解耦
        //2.强制代理，先要获取真实对象，然后真实对象会给一个指定的代理，再用代理去执行
        //3.代理模式主要是为了对真实对象的访问控制和功能增强，实现上就是代理持有真实对象，然后实现增强接口
        /**
         * 普通代理--普通代理是代理管理真实对象
         */
        Proxy proxy = new Proxy();
        proxy.request();

        /**
         * 强制代理
         */

        /*RealSubject realSubject = new RealSubject();
        realSubject.request();
        realSubject.getProxy().request();*/

        //校验是否自己指定的代理需要完善
        /*RealSubject realSubject = new RealSubject();
        Subject proxy = realSubject.getProxy();
        System.out.println("对象自己的代理:"+proxy);

        //还是自己去创建代理
        com.example.dp.proxy.qzproxy.Proxy proxy1 = new com.example.dp.proxy.qzproxy.Proxy(realSubject);
        System.out.println("执行的代理:"+proxy1);
        proxy1.request();*/

        /**
         * 测试动态代理
         * 实现原理
         * 1.使用realSubject.getClass().getInterfaces()去动态生成所有接口
         * 2.生成的所有接口直接调用invocationHandler的invoke方法
         * 3.具体方法怎么执行，放入切点位置和通知由我们实现handle自己控制
         * 4.如果需要代理多个类，那么我们需要实现特定类的方法的handle
         *
         * 动态代理和静态代理对吧
         * 1.动态代理不需要手写代理类，会自动生成
         * 2.静态代理在方法处理时也就是切入连接和通知应该当做专门业务代码处理，在动态代理就是handle，明显静态代理类生成和业务耦合在一起
         *   而动态代理分别由jdk动态代理实现代理类，handle去专门处理业务
         */
        RealSubject realSubject = new RealSubject();
        InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl(realSubject);
        //Subject proxyInstance = (Subject)java.lang.reflect.Proxy.newProxyInstance(realSubject.getClass().getClassLoader(), new Class[]{Subject.class}, invocationHandler);
        //proxyInstance.request();

        Subject proxyInstance=DynamicProxy.newProxyInstance(realSubject.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), invocationHandler);
        proxyInstance.request();
    }

}
