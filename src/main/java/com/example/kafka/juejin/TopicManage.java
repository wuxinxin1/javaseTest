package com.example.kafka.juejin;


/**
 * 其实命令行也调用jar里面的TopicCommand.main
 *
 * 我们直接使用这个脚本来管理Topic
 */
public class TopicManage {

    public static void main(String[] args) {

        String[] options = new String[]{
                "--zookeeper", "localhost:2181/kafka",
                "--create",
                "--replication-factor", "1",
                "--partitions", "1",
                "--topic", "topic-create-api"
        };
        kafka.admin.TopicCommand.main(options);

    }

}
