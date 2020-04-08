package com.example.kafka.juejin;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class DemoProducerInterceptor2 implements ProducerInterceptor {

    /**
     * 发送前执行
     * @param record
     * @return
     */
    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        System.out.println("拦截器DemoProducerInterceptor2:onSend");
        return record;
    }

    /**
     * 消息发送成功或者失败回调，早于我们的Callback
     * @param metadata
     * @param exception
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        System.out.println("拦截器DemoProducerInterceptor2:onAcknowledgement");
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
