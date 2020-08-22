package com.example.nio.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 单Reactor单线程模型
 */
class ReactorModel1 {

    private Reactor reactor;

    private Integer port;

    public ReactorModel1(Integer port) throws IOException {
        this.port = port;
        this.reactor=new Reactor(port);
    }

    public void start(){
        Thread thread = new Thread(this.reactor);
        thread.start();
    }

}

/**
 * 组件Reactor(事件分发)
 */
class Reactor implements Runnable{

    /**
     * 端口
     */
    private Integer prot;

    /**
     * 用于接受连接的ServerChannel
     */
    private ServerSocketChannel serverSocketChannel;


    /**
     * 管理连接的选择器
     */
    private Selector selector;

    public Reactor(Integer prot) throws IOException {
        this.prot = prot;
        this.serverSocketChannel = ServerSocketChannel.open();
        this.selector = Selector.open();

        //绑定端口
        this.serverSocketChannel.bind(new InetSocketAddress(prot));
        //设置为非阻塞
        this.serverSocketChannel.configureBlocking(false);
        //注册到选择器上面
        this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void run() {

        while (true){
            try {
                int select = selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {

                    SelectionKey selectionKey = iterator.next();

                    //分发事件
                    dispatch(selectionKey);

                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 进行事件分发
     * @param selectionKey
     */
    private void dispatch(SelectionKey selectionKey){

        if (selectionKey.isAcceptable()) {
            //连接事件
            new Acceptor(serverSocketChannel,selector).run();
        } else{
            //读写事件
            new Handler(selectionKey).run();
        }

    }

}

/**
 * accepor(处理读写)
 */
class Acceptor implements Runnable{

    /**
     * 用于接受连接的ServerChannel
     */
    private ServerSocketChannel serverSocketChannel;


    /**
     * 管理连接的选择器
     */
    private Selector selector;

    public Acceptor(ServerSocketChannel serverSocketChannel, Selector selector) {
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
    }

    @Override
    public void run() {

        //serverSocketChannel的连接事件
        SocketChannel accept = null;
        try {
            accept = this.serverSocketChannel.accept();
            //设置为非阻塞
            accept.configureBlocking(false);
            //注册到selector, 注册读写事件
            accept.register(selector,SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            System.out.println("新连接:"+accept.getRemoteAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Handler implements Runnable{

    private SelectionKey selectionKey;

    public Handler(SelectionKey selectionKey) {
        this.selectionKey = selectionKey;
    }

    @Override
    public void run() {

        try {
            if(selectionKey.isReadable()){
                //读事件处理
                this.read();
            }else {
                //写事件处理
                this.write();
            }
        }catch (Exception e){

        }


    }

    /**
     * 处理读事件
     * @throws IOException
     */
    private void read() throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();

        ByteBuffer allocate = ByteBuffer.allocate(1024);

        int read = channel.read(allocate);

        if(read>0) {
            System.out.println("接收到消息:" + new String(allocate.array(), 0,read));
        }
    }

    /**
     * 处理写事件
     * @throws IOException
     */
    private void write() throws IOException {

    }
}