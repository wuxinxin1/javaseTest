package com.example.kafka.juejin;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author wuxinxin'
 *
 * 生产者的构建
 *
 * 1.生产者必须指定key和value的序列化器{@link org.apache.kafka.clients.producer.ProducerConfig}指定比较好
 * 2.生产者有三种发送方式: 发后即忘（fire-and-forget）、同步（sync）及异步（async
 * 3.生产者发送一条消息，需要通过拦截器，序列化器，分区器(按照顺序的)
 *   a.发送前过滤一些不需要的消息，或者对消息做修改，回调一些定制化的需求
 *   b.序列化器将发送消息进行序列化和反序列化
 *   c.分区器用来将消息按照某种策略发送到对应的分区
 *
 * 4.消息在发送前先会被缓存到RecordAccumulator中，这个多大可以通过buffer.memory进行设置，默认32M。
 *   RecordAccumulator为每个分区分配一个队列，每个分区分别作了消息缓存，但是消息累加器重的元素不是ProducerRecord类型，而是
 *   ProducerBatch类型，代表一个消息批次，包含多个ProducerRecord，这样一次发送ProducerBatch，可以减少网络请求，提高吞吐量
 *
 * 5.生产者需要注意一些重要的参数
 *   a.acks,表明需要多少副本同步完成，那么认为写入成功，那么服务端会响应，设置为0的时候吞吐量最大，但是可靠性差
 *   b.max.request.size  生产者发送消息的最大值,默认1M
 *   c.retries 消息发送的最大重试次数
 *   d.compression.type 指定消息的压缩方式，压缩算法虽然需要时间，但是发送的消息经过压缩变小了，可以减少网络传输
 */
public class ProducerTest {

    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        /*properties.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        properties.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");*/

        //配置序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //采用自定义的分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,DemoPartiotioner.class.getName());

        //配置自定义的拦截器
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,DemoProducerInterceptor.class.getName()+","+DemoProducerInterceptor2.class.getName());

        properties.put("bootstrap.servers", brokerList);


        KafkaProducer<String, String> producer =
                new KafkaProducer<>(properties);

        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, "hello, Kafka!");

        //同步调用
        sync(producer,record);
        //异步调用
        //async(producer,record);

        producer.close();
    }


    //同步调用
    public static void  sync(KafkaProducer<String, String> producer,ProducerRecord<String, String> record) throws ExecutionException, InterruptedException {
        for(int i=0;i<2;i++) {
            RecordMetadata recordMetadata = producer.send(record).get();
            //可以获取发送后的元数据
            //主题-分区@下标
            System.out.println(recordMetadata);
        }

    }


    //异步调用
    public static void  async(KafkaProducer<String, String> producer,ProducerRecord<String, String> record) throws ExecutionException, InterruptedException {

        for(int i=0;i<2;i++) {
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    //metadata和exception互斥，成功exception为null,反之
                    if (exception != null) {
                        System.out.println("消息发送失败");
                    } else {
                        System.out.println(metadata);
                    }
                }
            });
        }
    }

}
