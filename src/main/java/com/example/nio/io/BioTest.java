package com.example.nio.io;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *  测试阻塞io(bio)
 * @author wuxinxin
 *
 * bio 会在accept,red,write 进行阻塞等待读写连接
 */
public class BioTest {



}

/**
 * 客户端
 */
class client1{


    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("localhost",8080));

        System.out.println(socketChannel.getLocalAddress());

        ByteBuffer wrap = ByteBuffer.wrap("hello".getBytes());

        socketChannel.write(wrap);


       /* //读取响应
        ByteBuffer allocate = ByteBuffer.allocate(1024);

        int num;
        System.out.println("客户端开始");
        while ((num=socketChannel.read(allocate))>0){
            allocate.flip();

            byte[] re = new byte[num];

            allocate.get(re);

            String s = new String(re);

            System.out.println("收到回复:"+s);

        }

        System.out.println("客户端结束");*/

    }

}

/**
 * 服务端
 */
class ServerBio{

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        while (true){
            SocketChannel accept = serverSocketChannel.accept();

            SocketHandle socketHandle = new SocketHandle(accept);

            new Thread(socketHandle).start();
        }

    }

}

class SocketHandle implements Runnable{

    private SocketChannel socketChannel;

    public SocketHandle(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {

        ByteBuffer allocate = ByteBuffer.allocate(1024);

        int num;

        System.out.println("服务端开始");
        try {
            while ((num=socketChannel.read(allocate))>0){

                allocate.flip();

                byte[] bytes = new byte[num];

                allocate.get(bytes);

                String s = new String(bytes);

                System.out.println("接收到:"+s);
            }
        }catch (Exception e){

        }

        System.out.println("服务端结束");

    }
}
