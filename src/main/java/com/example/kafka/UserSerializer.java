package com.example.kafka;

import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @author: wuxinxin
 * @version: v1.0
 * @description: com.example.kafka
 * @date:2019/6/6
 */
public class UserSerializer implements Serializer<User> {

    public UserSerializer() {

    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, User data) {
        try {
            String name = data.getName();
            String password = data.getPassword();

            ByteBuffer buffer = ByteBuffer.
                    allocate(4+4+name.length() + password.length());

            /*buffer.putInt(name.length());

            buffer.putInt(password.length());*/
            buffer.put(password.getBytes("utf-8")); buffer.put(name.getBytes("utf-8"));
            System.out.println("UserSerializer 序列化:"+buffer.toString());
            return buffer.array();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
