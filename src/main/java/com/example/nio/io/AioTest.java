package com.example.nio.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

/**
 * 异步io测试
 */
public class AioTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException, TimeoutException {

        fileAsyIO();

    }


    /**
     * nio中不支持fileChannel, 也就是对于FileChannel不支持非阻塞的，所以只能通过Aio来提高性能
     */
    public static void fileAsyIO() throws IOException, ExecutionException, InterruptedException, TimeoutException {

        long l = System.currentTimeMillis();
        AsynchronousFileChannel open = AsynchronousFileChannel.open(Paths.get("a.txt"));

        ByteBuffer allocate = ByteBuffer.allocate(1024);

        Future<Integer> read = open.read(allocate, 0);

        System.out.println(read.get());

        System.out.println(System.currentTimeMillis()-l);
        open.close();
    }

}
