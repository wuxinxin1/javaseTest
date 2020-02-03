package com.example.dp.handle;

/**
 * @author wuxinxin
 *
 *  处理器抽象类
 *
 */
public abstract class Handle {

    /**
     * 下一个处理器
     */
    private Handle nextHandle;

    /**
     * 处理模板方法
     * 1.看看处理标识符，看看能不能处理，能处理则处理
     * 2.如果不能处理，设置下一个处理器进行处理
     * @param request
     */
    public final void handleMessage(Request request){
        if(request.getLeavel()==this.getLeave()){
            this.beforeHandle(request);
        }else {
            nextHandle.handleMessage(request);
        }
    }

    /**
     * 真正处理的方法
     * @param request
     */
    protected abstract void beforeHandle(Request request);


    public void setNextHandle(Handle nextHandle) {
        this.nextHandle = nextHandle;
    }

    /**
     * 获取用户等级
     * @return
     */
    protected abstract int getLeave();
}
