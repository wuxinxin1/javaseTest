package com.example;

import com.sun.beans.decoder.DocumentHandler;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        try {
            //String path = "/sandbox/v2/transfer/internal";
            //String path = "/sandbox/v2/transfer/internal";
            String path="/v1/briva";
            //String verb = "GET";
            String verb = "POST";
            String token = "Bearer MAaTsV43lMBk2WTG90TajJdE0LjU";
            String timestamp = createTimestamp();
            String body = "{\n" +
                    "\t\"institutionCode\":\"J1044011\",\n" +
                    "\t\"brivaNo\":\"00001\",\n" +
                    "\t\"custCode\":\"123456789001\",\n" +
                    "\t\"nama\":\"wxx\",\n" +
                    "\t\"amount\":1000,\n" +
                    "\t\"keterangan\":\"\",\n" +
                    "\t\"expiredDate\":\"2020-01-03 21:00:00\"\n" +
                    "}";
            String key = "lEoweYxJso0eJEvx";

            String payload = "path=" + path + "&verb=" + verb + "&token=" + token +
                    "&timestamp=" + timestamp + "&body=" + body;

            String signatureString = createSignature(payload,key);

            System.out.println(signatureString);
            System.out.println(timestamp);
            System.out.println(payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String createTimestamp() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(tz);

        return df.format(new Date());
    }

    static String createSignature(String payload, String key) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        byte[] signatureByte = sha256_HMAC.doFinal(payload.getBytes("UTF-8"));

        return DatatypeConverter.printBase64Binary(signatureByte);
    }
}
