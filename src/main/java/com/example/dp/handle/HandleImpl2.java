package com.example.dp.handle;

public class HandleImpl2 extends Handle {

    @Override
    protected void beforeHandle(Request request) {
        System.out.println("处理 HandleImpl2 请求:"+request);
    }

    @Override
    protected int getLeave() {
        return RequestType.leavel2;
    }
}
