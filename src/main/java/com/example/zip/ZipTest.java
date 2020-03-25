package com.example.zip;

import java.io.ByteArrayOutputStream;
import java.util.zip.DeflaterOutputStream;

/**
 * 使用jdk自带压缩算法
 */
public class ZipTest {

    public static void main(String[] args) throws Exception {

        /**
         * 将字节压缩了
         */
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        java.util.zip.Deflater defeater = new java.util.zip.Deflater(5);
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, defeater);
        deflaterOutputStream.write(new String("abdsdsdsdfffvdef").getBytes());

        byte[] result = byteArrayOutputStream.toByteArray();

        System.out.println(new String(result));


    }

}
