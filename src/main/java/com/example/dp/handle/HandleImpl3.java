package com.example.dp.handle;

public class HandleImpl3 extends Handle {

    @Override
    protected void beforeHandle(Request request) {
        System.out.println("处理 HandleImpl3 请求:"+request);
    }

    @Override
    protected int getLeave() {
        return RequestType.leavel3;
    }
}
