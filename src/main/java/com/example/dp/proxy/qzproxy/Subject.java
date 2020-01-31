package com.example.dp.proxy.qzproxy;

/**
 * @author wuxinxin
 *
 * 普通的公司业务接口
 */
public interface Subject {
    /**
     * 业务执行方法
     */
    void request();

    /**
     * 获取自己的代理
     */
    Subject getProxy();
}
