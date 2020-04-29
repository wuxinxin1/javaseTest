package com.example.kafka.juejin;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

/**
 * @author wuxinxin
 *
 * 1.消费客户端订阅的时候，不仅可以直接订阅一个主题，还可以精确订阅一个topic的分区
 * 2.消费者和生产者需要配对的序列化和反序列化，如果变动，那么都需要变动，很大不方便，推荐使用序列化工具（Avro、JSON、Thrift、ProtoBuf 或 Protostuff）
 * 3. 消费者有两种模式，推和拉模式，拉模式用的比较多
 * 4.消费端消费了消息需要提交位移,提交的位移会持久化:
 *   1.默认情况下，消费端会自动的去提交位移（下一轮拉消息之前，延迟提交），这样编程复杂度降低很多，但是会带来重复消费和消息丢失的问题
 *   2.自动提交，如果在消费消息到一半的时候发生异常，那么这个消费了的消息的offset并没有提交，将导致重复消费
 *   3.自动提交，在某些情况下也会发生消息丢失
 *   4.手动提交位移分为两种类型：同步提交和异步提交
 *
 */
public class ComsumerTest {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";
    public static final String groupId = "group.demo";

    public static void main(String[] args) {
        Properties properties = new Properties();

        //配置发序列化器
        properties.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        /*properties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");*/
        properties.put("value.deserializer",
                CompanyDeserializer.class.getName());

        properties.put("value.deserializer",
                CompanyDeserializer.class.getName());

        properties.put("bootstrap.servers", brokerList);
        //配置消费组
        properties.put("group.id", groupId);

        //配置客户端id,否则默认分配
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG,"client.id.demo");

        //创建一个消费者客户端实例
        KafkaConsumer<String, Company> consumer = new KafkaConsumer<>(properties);
        //订阅主题
        //consumer.subscribe(Collections.singletonList(topic));
        //精确订阅到某个主题的分区
        ArrayList<TopicPartition> topicPartitions = new ArrayList<>();
        topicPartitions.add(new TopicPartition(topic,0));
        consumer.assign(topicPartitions);
        //需要精确的订阅分区，那么需要获取到主题的元数据信息
        List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
        System.out.println(partitionInfos);
        //循环消费消息
        while (true) {
            //轮训拉取有个时间，没有消息会阻塞这个设置的事件，有消息会马上获取到
            ConsumerRecords<String, Company> records =
                    consumer.poll(Duration.ofMillis(20000));

            for (ConsumerRecord<String, Company> record : records) {
                System.out.println("topic = " + record.topic()
                        + ", partition = "+ record.partition()
                        + ", offset = " + record.offset());
                System.out.println("key = " + record.key()
                        + ", value = " + record.value());
            }

            //消费完成，提交位移
            //consumer.commitSync();
            //System.out.println("提交消费位移");

            //看看committed offset, lastConsumedOffset,position之间的关系  position = committed offset = lastConsumedOffset + 1
            OffsetAndMetadata offsetAndMetadata = consumer.committed(new TopicPartition(topic, 0));
            System.out.println("commited offset is " + offsetAndMetadata.offset());

            long posititon = consumer.position(new TopicPartition(topic, 0));
            System.out.println("the offset of the next record is " + posititon);

            //按照分区维度消费
            /*for (TopicPartition tp : records.partitions()) {
                for (ConsumerRecord<String, Company> record : records.records(tp)) {
                    System.out.println(record.partition()+" : "+record.value());
                }
            }*/

            //按照主题维度消费,topic列表要自己定好，不像分区那样可以获取到

           /* for (String topic : Arrays.asList(topic)) {
                for (ConsumerRecord<String, Company> record :
                        records.records(topic)) {
                    System.out.println(record.topic() + " : " + record.value());
                }
            }*/

           //校验自动提交位移的情况下，造成重复消费
             //int i=1/0;

      System.out.println("拉取消息成功"+new Date());
        }

    }

}
