package com.example.nio.jdk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TalkClient {


    /**
     * 客户端channel
     */
    private SocketChannel socketChannel;

    /**
     * 客户端选择器
     */
    private Selector selector;


    /**
     * 初始化需要的参数
     * @throws IOException
     */
    public TalkClient() throws IOException {
        this.socketChannel = SocketChannel.open();
        this.selector = Selector.open();

        //设置为阻塞模式
        this.socketChannel.configureBlocking(false);
        //连接操作
        this.socketChannel.connect(new InetSocketAddress("127.0.0.1",6666));
        //注册到selector
        this.socketChannel.register(selector,SelectionKey.OP_CONNECT);
    }


    /**
     * 开始处理
     */
    public void start() throws IOException {

        while (true){

            int select = selector.select(2000);

            if(select>0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()){

                    SelectionKey selectionKey = iterator.next();

                    if(selectionKey.isConnectable()){
                        connectEvent();
                    }else if(selectionKey.isReadable()){

                    }

                    iterator.remove();
                }
            }

        }

    }

    /**
     * 服务器连接成功事件处理
     */
    private void connectEvent() throws IOException {
        socketChannel.finishConnect();
        ByteBuffer allocate = ByteBuffer.allocate(1024);

        allocate.put(("hello".getBytes()));

        allocate.flip();

        socketChannel.write(allocate);
    }

    public static void main(String[] args) throws Exception {
        TalkClient talkClient = new TalkClient();
        talkClient.start();
    }

}
