package com.example.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author: wuxinxin
 * @version: v1.0
 * @description: com.example.kafka
 * @date:2019/6/6
 */
public class ProducerFastStart {

    private static String brokerList="http://39.106.33.130:9092";
    public static String topic="topic-demo";

    public static void main(String[] args) throws Exception {
        Properties properties=new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,UserSerializer.class.getName());

        //创建生产者实例
        KafkaProducer<String,User> producer = new KafkaProducer<>(properties);

        User user = new User();
        user.setName("wxx");
        user.setPassword("123456");

        ProducerRecord<String, User> record =
                new ProducerRecord<>(topic,user );
        producer.send(record);

        producer.close();
    }

    public static void test4(){
        Properties properties=new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ProducerConfig.RETRIES_CONFIG, 10);

        //创建生产者实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        //可以指定分区发送消息
        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, 0,"","hello, Kafka! 222");

        for(int i=0;i<10;i++) {
            producer.send(record,new call());
        }

        producer.close();
    }

    static class call implements Callback {

        @Override
        public void onCompletion(RecordMetadata m, Exception e) {
            if (e != null) {
                e.printStackTrace();
            } else {
                System.out.println(m.topic() + "-" +
                        m.partition() + ":" + m.offset());
            }
        }
    }

    public static void test03(){
        Properties properties=new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ProducerConfig.RETRIES_CONFIG, 10);

        //创建生产者实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, "hello, Kafka! 1111");


        Future<RecordMetadata> send=null;
        for(int i=0;i<10;i++) {
            try {
                Long start = System.currentTimeMillis();
                //默认异步发送
                send = producer.send(record);

                //同步请求，会耗时，但是可靠
                //RecordMetadata metadata = send.get();

                //同步等待最长时间设置
                RecordMetadata metadata = send.get(50, TimeUnit.MILLISECONDS);

                Long end = System.currentTimeMillis();
                System.out.println(metadata.topic() + "-" +
                        metadata.partition() + ":" + metadata.offset() + ":耗时" + (end - start));
                System.out.println(i + "耗时:" + (end - start));
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                System.out.println("是否完成:"+send.isDone());
            }
        }


        producer.close();
    }

    public static void test02(){
        Properties properties=new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //创建生产者实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, "hello, Kafka! 1111");

        producer.send(record);

        producer.close();
    }


    public static void test01(){
        Properties properties=new Properties();
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers", brokerList);

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, "hello, Kafka!");

        producer.send(record);

        producer.close();
    }


}


