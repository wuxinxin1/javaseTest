package com.example.cipter;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import java.security.spec.EncodedKeySpec;

/**
 * 对称加密DES算法测试
 *
 */
public class DesTest {


    public static void main(String[] args) throws Exception{

        String content="wxx";

        Cipher cipher = Cipher.getInstance("DES");

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");

        //cipher.init(Cipher.ENCRYPT_MODE,secretKeyFactory);


    }

}
