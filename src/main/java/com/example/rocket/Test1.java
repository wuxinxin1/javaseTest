/*
package com.example.rocket;


import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

*/
/**
 * 队列的生产者和消费者
 *//*

 class Test1{
    public static void main(String[] args) throws Exception {

        //synSend();
        //synSend2();
        //receive1();
        //receive2();

        //测试消费速度
        receive3();

    }

    //消息发送的三种发送方式

    */
/**
     * 同步发送
     * @throws Exception
     *//*

    public static void synSend() throws Exception{
        DefaultMQProducer producer = new
                DefaultMQProducer("please_rename_unique_group_name");

        //producer.setNamesrvAddr("39.106.33.130:9876");
        producer.setNamesrvAddr("localhost:9876");

        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest",
                    "TagA" ,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

    */
/**
     * 选择发送的队列
     * @throws Exception
     *//*

    public static void synSend2() throws Exception{
        DefaultMQProducer producer = new
                DefaultMQProducer("please_rename_unique_group_name");

        producer.setNamesrvAddr("39.106.33.130:9876");

        producer.start();
        for (int i = 0; i < 1000; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest",
                    "TagA" ,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            //选择队列发送
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    System.out.println("消息队列长度"+list.size());
                    return list.get(0);
                }
            },i);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

    */
/**
     * 接收消息
     * 1.DefaultMQPushConsumer
     * 2.
     *//*

    public static void receive1() throws Exception{
        DefaultMQPushConsumer consumer = new
                DefaultMQPushConsumer("please_rename_unique_group_name");

        consumer.setNamesrvAddr("39.106.33.130:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        //MessageModel的两种消费模式
        //1.BROADCASTING 同一个消费组里面的消费者都会消费队列所有信息
        //2.CLUSTERING 同一组的消费者只会消费其中一部分，可以增强系统吞吐量
        consumer.setMessageModel(MessageModel.CLUSTERING);

        consumer.subscribe("TopicTest","*");

        consumer.registerMessageListener(new MessageListenerConcurrently(){

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println("收到消息"+list.size()+list);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
    }


    public static void receive2() throws Exception{
        DefaultMQPushConsumer consumer = new
                DefaultMQPushConsumer("please_rename_unique_group_name");

        consumer.setNamesrvAddr("39.106.33.130:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.setMessageModel(MessageModel.CLUSTERING);

        consumer.subscribe("TopicTest","*");

        //使用MessageListenerOrderly 实现同一队列不并发处理
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                System.out.println(msgs.get(0));
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
    }


    */
/**
     * 接收消息--如何增加消费的速度
     * 1.一个实例修改线程数量，增加一个组消费实例
     * 2.设置每次获取消息的最大个数，默认是1
     * 3.直接不处理消息，计算当前堆积大于某个阈值的时候
     *//*

    public static void receive3() throws Exception{

        final Long start=System.currentTimeMillis();

        DefaultMQPushConsumer consumer = new
                DefaultMQPushConsumer("please_rename_unique_group_name");

        consumer.setNamesrvAddr("localhost:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.setMessageModel(MessageModel.CLUSTERING);

        //consumer.setConsumeThreadMin(1);
        //consumer.setConsumeThreadMax(1);

        //consumer.setConsumeMessageBatchMaxSize(1000);

        consumer.subscribe("TopicTest","*");

        consumer.registerMessageListener(new MessageListenerConcurrently(){

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                //每条消息会携带详细和队列相关信息，可以计算还剩下多少条未处理，设置一个阈值就行丢弃处理
                MessageExt messageExt = list.get(0);
                System.out.println("收到消息"+list.size()+list+"耗时:"+(System.currentTimeMillis()-start));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
    }

}
*/
