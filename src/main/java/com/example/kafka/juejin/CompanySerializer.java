package com.example.kafka.juejin;

import com.example.protostuff.ProtoStuffUtils;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class CompanySerializer implements Serializer<Company> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Company data) {
        return ProtoStuffUtils.serialize(data);
    }

  /*  @Override
    public byte[] serialize(String topic, Company data) {

        byte[] name,age;

        if(data.getName()!=null){
            name=data.getName().getBytes();
        }else {
            name=new byte[0];
        }

        if(data.getAge()!=null){
            age=data.getAge().getBytes();
        }else {
            age=new byte[0];
        }

        ByteBuffer allocate = ByteBuffer.allocate(4 + 4 + name.length + age.length);

        allocate.putInt(name.length);
        allocate.put(name);
        allocate.putInt(age.length);
        allocate.put(age);

        return allocate.array();
    }*/

    @Override
    public void close() {

    }
}
