package com.example.nio.jdk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wuxinxin
 */
public class TalkService {

    /**
     * 用于接受连接的ServerChannel
     */
    private ServerSocketChannel serverSocketChannel;


    /**
     * 管理连接的选择器
     */
    private Selector selector;


    /**
     * 初始化必要的参数
     * @throws Exception
     */
    public TalkService() throws Exception {
        this.serverSocketChannel = ServerSocketChannel.open();
        this.selector = Selector.open();

        //绑定端口
        this.serverSocketChannel.bind(new InetSocketAddress(6666));
        //设置为非阻塞
        this.serverSocketChannel.configureBlocking(false);
        //注册到选择器上面
        this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }


    /**
     * 启动服务端，开始监听端口，分发处理
     */
    public void start() throws Exception {

        while (true) {
            int select = selector.select();

            if (select > 0) {

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {

                    SelectionKey selectionKey = iterator.next();

                    if (selectionKey.isAcceptable()) {
                        //新连接
                        acceptEvent();
                    } else if (selectionKey.isReadable()) {
                        //读取channel事件
                        readEvent(selectionKey);
                    }

                    iterator.remove();
                }
            }
        }

    }

    /**
     * 处理接受新连接
     */
    public void  acceptEvent() throws Exception {
        //serverSocketChannel的连接事件
        SocketChannel accept = this.serverSocketChannel.accept();
        //设置为非阻塞
        accept.configureBlocking(false);
        //注册到selector
        accept.register(selector,SelectionKey.OP_READ);

        System.out.println("新连接:"+accept.getRemoteAddress());
    }


    /**
     * 处理读事件
     */
    public void readEvent(SelectionKey selectionKey) throws IOException {

        SocketChannel channel = (SocketChannel) selectionKey.channel();

        ByteBuffer allocate = ByteBuffer.allocate(1024);

        int read = channel.read(allocate);

        if(read>0) {
            System.out.println("接收到消息:" + new String(allocate.array(), 0,read));
        }


    }

    public static void main(String[] args) throws Exception {
        TalkService talkService = new TalkService();

        talkService.start();
    }
}
