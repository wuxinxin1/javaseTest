package com.example.websocket;

import java.io.IOException;
import java.text.DateFormat;

public class Test {

    public static void main(String[] args) throws IOException {


       /* WebSocketTest ws = new WebSocketTest();

        ws.onMessage("");

        System.in.read();*/

        String i = String.valueOf("2020-08-01 00:00:00".replace("-", "")).replace(":","").replace(" ","");

        System.out.println(i);
    }

}
