package com.example.redis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 根据redis协议，手动编写redis客户端工具
 * Created by Administrator on 2019/7/12/012.
 */
public class RESPTest {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 6379);
        OutputStream outputStream = socket.getOutputStream();

        //获取resp协议数据,发送命令
        String resp = resp("set name1 wxxx");

        outputStream.write(resp.getBytes());

        outputStream.flush();

        //读取返回结果
        InputStream inputStream = socket.getInputStream();
        byte[] buf=new byte[1024];
        int len=0;
        while ((len=inputStream.read(buf))!=-1){
            System.out.println(new String(buf,0,len));
        }
        inputStream.close();
        outputStream.close();
    }

    /**
     * 生成redis协议格式数据
     */
    private static String resp(String command){
        //解析命令
        String [] params=command.split(" ");

        //拼装命令
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("*"+params.length+"\r\n");

        for (String param:params){
            stringBuffer.append("$"+param.length()+"\r\n"+param+"\r\n");
        }
        return stringBuffer.toString();
    }
}
