package com.example.websocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/webSocket/{username}")
public class WebSocketTest {

    private static int onlineCount = 0;

    private static Map<String, WebSocketTest> clients = new ConcurrentHashMap<String, WebSocketTest>();

    private Session session;

    private String username;



    @OnOpen

    public void onOpen(@PathParam("username") String username, Session session) throws IOException {



        this.username = username;

        this.session = session;



        addOnlineCount();

        clients.put(username, this);

        System.out.println("已连接");

    }



    @OnClose

    public void onClose() throws IOException {

        clients.remove(username);

        subOnlineCount();

    }



    @OnMessage

    public void onMessage(String message) throws IOException {

        sendMessageAll("给所有人");

    }



    @OnError

    public void onError(Session session, Throwable error) {

        error.printStackTrace();

    }



    public void sendMessageTo(String message, String To) throws IOException {

        // session.getBasicRemote().sendText(message);

        //session.getAsyncRemote().sendText(message);

        for (WebSocketTest item : clients.values()) {

            if (item.username.equals(To) )

                item.session.getAsyncRemote().sendText(message);

        }

    }



    public void sendMessageAll(String message) throws IOException {

        for (WebSocketTest item : clients.values()) {

            item.session.getAsyncRemote().sendText(message);

        }

    }



    public static synchronized int getOnlineCount() {

        return onlineCount;

    }



    public static synchronized void addOnlineCount() {

        WebSocketTest.onlineCount++;

    }



    public static synchronized void subOnlineCount() {

        WebSocketTest.onlineCount--;

    }



    public static synchronized Map<String, WebSocketTest> getClients() {

        return clients;

    }

}