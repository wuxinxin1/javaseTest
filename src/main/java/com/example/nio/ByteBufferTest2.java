package com.example.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 使用直接缓冲区
 * Created by Administrator on 2019/3/23/023.
 */
public class ByteBufferTest2 {

    private ByteBuffer byteBuffer=ByteBuffer.allocateDirect(1024);

    /**
     * 测试基本功能
     */
    @Test
    public void test01() {
        print("init");
        put("abcde");
        System.out.println(byteBuffer.isDirect());
    }

    private void put(String date){
        byteBuffer.put(date.getBytes());
        print("put");
    }



    private void print(String func){
        System.out.println("==========="+func+"===========");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
    }
}
