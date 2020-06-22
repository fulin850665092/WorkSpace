package com.at.guigu.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

/**
 * 自定义offset储存
 */
public class ConsumerRebalanceListener2 {


    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092,hadoop103:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"bigdata-0213");
        //earliest,从最开头消费，第一次消费的时候才起作用；默认是latest，消费最新的
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("first"), new org.apache.kafka.clients.consumer.ConsumerRebalanceListener() {
            //rabalance之前调用
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                System.out.println("before rabalance");
                for (TopicPartition topicPartition : collection) {
                    System.out.println(topicPartition);
                }
            }
            //rabalance之后调用
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                System.out.println(" after rabalance");
                for (TopicPartition topicPartition : collection) {
                    System.out.println(topicPartition);
                }
            }
        });

        try {
            while (true) {
                //pull提交
                ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : poll) {
                    System.out.println(record);
                }
            }
        } finally {
            consumer.close();
        }

    }
}

