package com.example.dp.handle;

public class HandleImpl1 extends Handle {

    @Override
    protected void beforeHandle(Request request) {
        System.out.println("处理 HandleImpl1 请求:"+request);
    }

    @Override
    protected int getLeave() {
        return RequestType.leavel1;
    }
}
