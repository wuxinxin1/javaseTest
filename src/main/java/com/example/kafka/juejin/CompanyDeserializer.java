package com.example.kafka.juejin;

import com.example.protostuff.ProtoStuffUtils;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CompanyDeserializer implements Deserializer<Company> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Company deserialize(String topic, byte[] data) {
        return ProtoStuffUtils.deserialize(data,Company.class);
    }

    /*@Override
    public Company deserialize(String topic, byte[] data){

        try {
            ByteBuffer wrap = ByteBuffer.wrap(data);

            int nameLen,ageLen;
            String name,age;

            nameLen=wrap.getInt();
            byte[] bytes1 = new byte[nameLen];
            wrap.get(bytes1);

            ageLen=wrap.getInt();
            byte[] bytes = new byte[ageLen];
            wrap.get(bytes);
            age=new String(bytes,"utf-8");
            name=new String(bytes1,"utf-8");
            return new Company(name,age);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

       return null;
    }*/

    @Override
    public void close() {

    }
}
