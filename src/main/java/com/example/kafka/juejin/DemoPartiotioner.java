package com.example.kafka.juejin;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 自定义分区器，这个可以根据你的策略来发送到哪个分区，会覆盖掉默认的分区器{@link org.apache.kafka.clients.producer.internals.DefaultPartitioner}
 */
public class DemoPartiotioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        System.out.println("分区器DemoPartiotioner:partition");
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
