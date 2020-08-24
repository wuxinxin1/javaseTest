package com.example.nio.netty.mainsub;

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
 * 主从单Reactor单线程模型
 */
class MainSubReactorModel {

    private MainReactor mainReactor;

    private SubReactor subReactor;

    private Integer port;

    public MainSubReactorModel(Integer port) throws IOException {
        this.port = port;
        this.subReactor=new SubReactor();
        this.mainReactor=new MainReactor(this.port,this.subReactor);

    }

    public void start(){
        //启动主Reactor
        Thread thread = new Thread(this.mainReactor);
        thread.start();
        //启动从Reactor
        Thread thread1 = new Thread(this.subReactor);
        thread1.start();
    }

}

/**
 * 组件主Reactor(单独一个Selector处理连接事件)
 */
class MainReactor implements Runnable{

    /**
     * 端口
     */
    private Integer prot;

    /**
     * 用于接受连接的ServerChannel
     */
    private ServerSocketChannel serverSocketChannel;

    /**
     * 组合子Reactor
     */
    private SubReactor subReactor;

    /**
     * 管理连接的选择器
     */
    private Selector selector;

    public MainReactor(Integer prot,SubReactor subReactor) throws IOException {
        this.prot = prot;
        this.serverSocketChannel = ServerSocketChannel.open();
        this.selector = Selector.open();
        this.subReactor=subReactor;

        //绑定端口
        this.serverSocketChannel.bind(new InetSocketAddress(this.prot));
        //设置为非阻塞
        this.serverSocketChannel.configureBlocking(false);
        //注册到选择器上面
        this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void run() {

        while (true){
            try {
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isAcceptable()) {
                        //处理连接
                        new Acceptor(serverSocketChannel, subReactor.getSelector()).run();
                    }

                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

/**
 * 组件从Reactor(单独一个Selector处理读写事件)
 */
class SubReactor implements Runnable {


    /**
     * 管理连接的选择器
     */
    private Selector selector;

    public Selector getSelector() {
        return selector;
    }

    public SubReactor() throws IOException {
        this.selector = Selector.open();
    }

    @Override
    public void run() {

        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {

                    SelectionKey selectionKey = iterator.next();

                    //Handler处理读写事件
                    new Handler(selectionKey).run();

                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
     * 管理新连接的channel
     */
    private Selector selector;

    public Acceptor(ServerSocketChannel serverSocketChannel, Selector selector) {
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
    }

    @Override
    public void run() {

        SocketChannel accept = null;
        try {
            accept = this.serverSocketChannel.accept();
            //设置为非阻塞
            accept.configureBlocking(false);
            //注册到selector, 注册读写事件
            accept.register(this.selector,SelectionKey.OP_READ | SelectionKey.OP_WRITE);

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

