package com.example.dp.handle;

public class HandleContext {

    public static void processor(Request request){
        //责任链模式总是从一个固定的处理器开始
        Handle handle = new HandleImpl1();
        HandleImpl2 handleImpl2 = new HandleImpl2();
        handle.setNextHandle(handleImpl2);
        HandleImpl3 handleImpl3 = new HandleImpl3();
        handleImpl2.setNextHandle(handleImpl3);

        handle.handleMessage(request);
    }

}
