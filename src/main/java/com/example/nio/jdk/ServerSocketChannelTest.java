package com.example.nio.jdk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerSocketChannelTest {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(6666));

        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){

            int select = selector.select(2000);

            if (select>0){

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();

                    if(next.isAcceptable()){
                        SocketChannel accept = serverSocketChannel.accept();
                        System.out.println("新链接："+accept.getRemoteAddress());
                        accept.configureBlocking(false);
                        accept.register(selector,SelectionKey.OP_READ);
                    }

                    if(next.isReadable()){
                        SocketChannel channel = (SocketChannel)next.channel();

                        ByteBuffer allocate = ByteBuffer.allocate(1024);

                        int read = channel.read(allocate);

                        System.out.println("服务端接收到消息:"+new String(allocate.array(),read));

                        channel.write(allocate);

                    }

                    iterator.remove();
                }

            }else {
                System.out.println("没连接");
            }

        }

    }

}
