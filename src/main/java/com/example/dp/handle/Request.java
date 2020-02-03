package com.example.dp.handle;

/**
 * 责任链模式的统一请求
 */
public class Request {

    /**
     * 请求唯一标识，指定了某种处理器可以处理
     */
    private int leavel;

    public int getLeavel() {
        return leavel;
    }

    public void setLeavel(int leavel) {
        this.leavel = leavel;
    }

    public Request(int leavel) {
        this.leavel = leavel;
    }
}
