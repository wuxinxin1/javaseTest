package com.example.dp.handle;

/**
 * @author wuxinxin
 *
 * 责任链中的处理器处理完的统一响应
 */
public class Response {
    /**
     * 标识链中哪个处理器处理的
     */
    private int leavel;

    /**
     * 处理完的描述
     */
    private String desc;

}
