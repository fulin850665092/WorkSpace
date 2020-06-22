package com.at.guigu.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Comsumer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        //反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        //设置消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"custom3");
        //设置自动提交（默认是5秒提交一次，可能会造成重复消费）
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        //earliest,从最开头消费，（消费者组）第一次消费的时候才起作用；默认是latest，消费最新的
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //先订阅
        consumer.subscribe(Arrays.asList("first"));
        try {
            while (true) {
                //消费
                ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> consumerRecord : poll) {
                    System.out.println(consumerRecord);
//                    System.out.println("offset："+consumerRecord.offset()+"分区："+consumerRecord.partition()+"消费成功了！");
                }
            }
        }finally {
            //关闭
            consumer.close();
        }

    }
}
