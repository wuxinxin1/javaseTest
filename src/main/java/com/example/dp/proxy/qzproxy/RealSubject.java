package com.example.dp.proxy.qzproxy;


/**
 * @author wuxinxin
 *
 * 被代理类--真正执行业务者
 */
public class RealSubject implements Subject {

    private Subject proxy;

    @Override
    public void request() {
        if(isProxy()) {
            System.out.println("执行业务");
        }else {
            System.out.println("执行失败，请找代理");
        }
    }

    private boolean isProxy() {
        if(proxy!=null){
            return true;
        }
        return false;
    }

    /**
     * //真实对象告诉调用者自己的代理，然后调用代理去执行
     * @return
     */

    @Override
    public Subject getProxy() {
        this.proxy=new Proxy(this);
        return this.proxy;
    }
}
