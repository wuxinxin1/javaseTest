package com.example.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 使用非直接缓冲区
 * Created by Administrator on 2019/3/23/023.
 */
public class ByteBufferTest {

    private ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

    /**
     * 测试基本功能
     */
    @Test
    public void test01() {
        print("init");
        put("abcde");
        flip();
        get();
        rewind();
        remain();
    }

    /**
     * 写和读模式多次切换,不可以多次切换
     * 1.不能多次切换，多次切换将导致byteBuffer不可用(只可切换一次)
     * 2.其实flip不能真正意义上进行读写模式的切换
     */
    @Test
    public void test02(){
        print("init");
        put("abcde");
        flip();
        flip();
        flip();
        put("aaaa");
    }

    /**
     * 查看数据多少可用，其实就是计算position到limit之间的字节数量
     */
    private void remain(){
        if(byteBuffer.hasRemaining()){
            int remaining = byteBuffer.remaining();
            System.out.println("剩余:"+remaining+"数据可用");
        }else {
            System.out.println("没有数据可用");
        }
    }

    /**
     * rewind 就是将position的位置设置为0,mark设置为-1
     */
    private void rewind(){
        byteBuffer.rewind();
        print("rewind");
    }

    private void flip(){
        byteBuffer.flip();
        print("flip");
    }

    private void get(){
        byte b = byteBuffer.get();
        //System.out.println((char)b);
        print("get");
    }

    private void put(String date){
        byteBuffer.put(date.getBytes());
        print("put");
    }


    /**
     * 三个重要属性: position,limit,capacity
     * position:当前操作的位置
     * limit：当前模式下，你允许操作的最大位置(分为读和写的模式:如果为读，那么为能读到的数据的最大下标;
     *        如果为写，那么为能写到的数据的最大下标;)
 *     capacity:当前分配数组的总容量
     * @param func
     */
    private void print(String func){
        System.out.println("==========="+func+"===========");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
    }
}
