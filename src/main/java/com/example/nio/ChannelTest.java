package com.example.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 1.channel(通道)，用于连接源节点和目标节点，本身不存储数据，只是用于数据的传输，数据主要存储在缓冲区中
 * 2.通道的主要实现类
 *   FileChannel    用于本地文件的传输通道
 *   socketChannel  用于TCP客户端传输通道
 *   SocketServerChannel  用于TCP服务端传输通道
 *   DataGramChannel      用于UDP传输通道
 *
 * 3.获取channel的方式：
 *   1.支持Channel的流都会提供一个getChannel()的方法用于获取Channel
 *     本地：FileInputStream/FileOutputStream  AccessRandomFile
 *     网络: socket /serverSocket  /DateGramSocket
 *
 *   2.jdk1.7以后，针对各个通道都有静态方法open()获取Channel
 *   3.jdk1.7以后，Files工具类的newByteChannel()获取Channel
 *
 * Created by Administrator on 2019/3/23/023.
 */
public class ChannelTest {


    /**
     * 测试获取Channel,channel使用的是非直接缓冲区
     */
    @Test
    public void test01() throws Exception{

        long start = System.currentTimeMillis();

        FileInputStream fileInputStream=new FileInputStream("F:/java架构师视频/nio.zip");
        FileOutputStream fileOutputStream=new FileOutputStream("F:/java架构师视频/nio3.zip");

        //获取Channel
        FileChannel channel_1 = fileInputStream.getChannel();
        FileChannel channel_2 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024*1024*1000);
        print(byteBuffer,"init");
        while (channel_1.read(byteBuffer)!=-1){//将数据读取到byteBuffer中
            print(byteBuffer,"write");
            //将byteBuffer转换为写的模式
            byteBuffer.flip();
            print(byteBuffer,"read");
            channel_2.write(byteBuffer);
            //清除一下缓冲区
            byteBuffer.clear();
            print(byteBuffer,"clear");
        }

        channel_1.close();
        channel_2.close();
        fileInputStream.close();
        fileOutputStream.close();
        long end = System.currentTimeMillis();

        System.out.println("耗时:"+(end-start));//10666
    }


    /**
     * 测试获取Channel,channel使用的是直接缓冲区
     *
     * MappedByteBuffer  -> MappedByteBuffer  直接缓冲区直接的copy
     *
     */
    @Test
    public void test02() throws Exception{

        long start = System.currentTimeMillis();

        FileChannel open = FileChannel.open(Paths.get("F:/","java架构师视频","nio.zip"), StandardOpenOption.READ);
        FileChannel open1 = FileChannel.open(Paths.get("F:/","java架构师视频","nio2.zip"), StandardOpenOption.WRITE,StandardOpenOption.READ,
                StandardOpenOption.CREATE);

        //获取直接缓存区
        MappedByteBuffer map = open.map(FileChannel.MapMode.READ_ONLY, 0, open.size());
        MappedByteBuffer map1 = open1.map(FileChannel.MapMode.READ_WRITE, 0, open.size());

        //直接对直接缓冲区进行数据操作
        byte[] dst=new byte[map.limit()];
        map.get(dst);
        map1.put(dst);

        open.close();
        open1.close();

        long end = System.currentTimeMillis();

        System.out.println("耗时:"+(end-start));//10666

    }

    /**
     * 测试获取Channel,channel使用的是直接缓冲区
     *
     * FileChannel -> FileChannel  通道直接的copy
     *
     */
    @Test
    public void test03() throws Exception{

        long start = System.currentTimeMillis();

        FileChannel open = FileChannel.open(Paths.get("F:/","java架构师视频","nio.zip"), StandardOpenOption.READ);
        FileChannel open1 = FileChannel.open(Paths.get("F:/","java架构师视频","nio2.zip"), StandardOpenOption.WRITE,StandardOpenOption.READ,
                StandardOpenOption.CREATE);

        //通道之间的复制
        //open.transferTo(0,open.size(),open1);
        open1.transferFrom(open,0,open.size());

        open.close();
        open1.close();

        long end = System.currentTimeMillis();

        System.out.println("耗时:"+(end-start));

    }

    /**
     * 测试 分散读取和聚居写入
     */
    @Test
    public void test4() throws Exception{

        RandomAccessFile randomAccessFile=new RandomAccessFile("a.txt","rw");

        FileChannel channel = randomAccessFile.getChannel();

        //分散读取，将读取的内容依次填充缓冲区
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(5);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(20);

        ByteBuffer[] bfs={byteBuffer1,byteBuffer2};
        //查看buf的情况
        print(byteBuffer1,"byteBuffer1");
        print(byteBuffer2,"byteBuffer2");

        //读取到缓冲区
        channel.read(bfs);
        //查看buf的情况
        print(byteBuffer1,"byteBuffer1");
        print(byteBuffer2,"byteBuffer2");

        channel.close();
        randomAccessFile.close();
    }

    private void print(ByteBuffer byteBuffer,String func){
        System.out.println("==========="+func+"===========");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
    }
}
