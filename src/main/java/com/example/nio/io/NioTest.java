package com.example.nio.io;

import sun.nio.ch.KQueueSelectorProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wuxinxin
 *
 *  非阻塞io测试（nio）
 *
 */
public class NioTest {

    public static void main(String[] args) throws IOException {

        //开启一个selector
        Selector open = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(8080));

        //将channel注册到selector
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(open,serverSocketChannel.validOps());

        while (true){
            int select = open.select();

            if(select==0){
                continue;
            }

            Set<SelectionKey> selectionKeys = open.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey next = iterator.next();

                iterator.remove();

                if(next.isAcceptable()){
                    //获得与SelectionKey相连的ServerSocketChannel
                    ServerSocketChannel ssc = (ServerSocketChannel) next.channel();
                    //获得与客户端连接的SocketChannel
                    SocketChannel sockChannel = ssc.accept();
                    System.out.println("接收到客户端连接，来自：" + sockChannel.socket().getInetAddress() + ":"
                            + sockChannel.socket().getPort());

                    //设置SocketChannel为非阻塞模式
                    sockChannel.configureBlocking(false);

                    sockChannel.register(open,sockChannel.validOps());
                }else if(next.isReadable()){

                    SocketChannel socketChannel=(SocketChannel)next.channel();

                    ByteBuffer allocate = ByteBuffer.allocate(1024);

                    int read = socketChannel.read(allocate);

                    if(read==-1) continue;

                    allocate.flip();

                    byte[] bytes=new byte[read];

                    allocate.get(bytes);

                    System.out.println("收到数据:"+new String(bytes));

                }else if(next.isWritable()) {
                    System.out.println("不知道");
                }
            }
        }
    }

}
