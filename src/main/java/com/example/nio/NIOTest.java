package com.example.nio;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author wuxinxin
 *  通道使用测试
 */
  class NIOTest2{

    public static void main(String[] args) throws Exception{

        //测试buffer
        //test1();
        //测试通道读写
        //test2();
        //测试selector
        //test3();
        //使用阻塞式ServerSocketChannel
        //test4();
        //使用非阻塞式ServerSocketChannel
        //test5();

        //阻塞SocketChannel
        //test6();

        //阻塞Socket
        test8();

        //非阻塞SocketChannel
        //test7();
    }


    /**
     * ByteBuffer有两种模式，读和写模式
     * 1.position()函数可以获取到读写模式分别开始的位置
     * 2.limit()函数可以获取读写模式分别的结束位置
     * 3.flip用于切换读写模式
     * 4.管道内部实现也是直接对Buffer进行操作的
     *   a.所以如果是向channel写入一个Buffer,那么需要允许channel先读到数据，那么在写入前要手动切为读模式，这样才能获取到正确的position和limit
     *   b.所以如果是向channel读出一个Buffer，那么需要允许channel能写入数据，那么在写入前要手动切为写模式，这样才能获取到正确的position和limit
     * @throws Exception
     */
    public static void test1() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("a.txt");

        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer allocate = ByteBuffer.allocate(100);
        channel.read(allocate);
        print(allocate,"test1");
        //切换到读模式
        allocate.flip();
        print(allocate,"test1");
        //读取字节查看位置情况
    }

    /**
     * 测试通道读写
     * 1. 使用FileChannel.open获取通道，这样可以自由选择读写支持，否则使用传统io获取通道，会受到读写流的控制，从而影响通道不支持读写两种操作
     * 2.使用channel写的时候，需要将buffer转为读，这样channel才能正确读（position和limit更新）
     * @throws Exception
     */
    public static void test2()throws Exception{
        FileChannel channel = FileChannel.open(Paths.get("a.txt"), StandardOpenOption.WRITE, StandardOpenOption.READ);
        //写数据
        /*//先准备好数据，方便计算长度
        String s="吴鑫鑫1";
        int len=s.getBytes().length;
        ByteBuffer byteBuffer = ByteBuffer.allocate(len);
        print(byteBuffer,"test2");
        //默认写模式，写入数据
        byteBuffer.put(s.getBytes());
        print(byteBuffer,"test2");
        //必须要切换为写模式，这样通道可以获取position和limit进行读取
        byteBuffer.flip();
        channel.write(byteBuffer);
        channel.close();*/
        //读数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        print(byteBuffer,"test2");
        //channel读取数据，写入到buffer
        channel.read(byteBuffer);
        print(byteBuffer,"test2");
        //转成读模式输出
        byteBuffer.flip();
        print(byteBuffer,"test2");
        //读取其中数据
        System.out.println("读到数据:"+new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit()));
        //不能再切回去了
        byteBuffer.flip();
        print(byteBuffer,"test2");
        byteBuffer.flip();
        print(byteBuffer,"test2");
        channel.close();
    }

    private static void print(ByteBuffer byteBuffer,String func){
        System.out.println("==========="+func+"===========");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
    }

    /**
     * 测试
     * @throws Exception
     * 1.只有是非阻塞的Channel 才能注册到selector上，所以要先设置为非阻塞
     * 2.注册时需要指明对channel哪些事件感兴趣Connect(连接上) Accept（接收） Read（读就绪） Write（写就绪）
     *
     */
    public static void test3()throws Exception{
        //创建一个selector
        Selector selector = Selector.open();
        //创建一个channel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        //channel注册到selector上
        SelectionKey register = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        //System.out.println(register.);
    }


    /**
     *
     * 阻塞式 ServerSocketChannel,accept一直阻塞，直到接收到连接
     * @throws Exception
     */
    public static void test4()throws Exception{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",9999));

        while (true){
            System.out.println("获取连接--开始");
            SocketChannel accept = serverSocketChannel.accept();
            System.out.println("获取连接--结束");
            ByteBuffer allocate = ByteBuffer.allocate(5);
            print(allocate,"test4");
            accept.read(allocate);
            print(allocate,"test4");
            allocate.flip();
            print(allocate,"test4");
            //读取会移动
            System.out.println(allocate.remaining());
            //读取不会移动
            //System.out.println(new String(allocate.array(),allocate.position(),allocate.limit()));
            print(allocate,"test4");
            //accept.close();
        }
    }

    /**
     *
     * 非阻塞式 ServerSocketChannel,accept方法会立即返回，没有获取到就返回null
     * @throws Exception
     */
    public static void test5()throws Exception{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(9999));

        serverSocketChannel.configureBlocking(false);

        while (true){
            SocketChannel accept = serverSocketChannel.accept();
            System.out.println("获取连接--"+accept);
        }
    }

    /**
     *
     * 阻塞式 SocketChannel
     * @throws Exception
     */
    public static void test6()throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",9999));

        ByteBuffer allocate = ByteBuffer.allocate(10);
        allocate.put(new String("aaaa").getBytes());

        allocate.flip();
        socketChannel.write(allocate);

        //读取
        socketChannel.read(allocate);

        socketChannel.close();
    }

    /**
     *
     * 阻塞式 SocketChannel
     * @throws Exception
     */
    public static void test8()throws Exception{
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",9999));

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(new String("aa").getBytes());
        outputStream.close();

        InputStream inputStream = socket.getInputStream();
        inputStream.read();

        //socket.close();
    }

    /**
     *
     * 非阻塞式 SocketChannel
     * 1.非阻塞式 SocketChannel不能连接阻塞ServerSocketChannel
     * @throws Exception
     */
    public static void test7()throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost",9999));

        ByteBuffer allocate = ByteBuffer.allocate(10);
        allocate.put(new String("aaaa").getBytes());

        allocate.flip();
        socketChannel.write(allocate);

        socketChannel.close();
    }


}
