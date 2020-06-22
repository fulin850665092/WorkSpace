package com.at.guigu.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

/**
 * 手动异步提交
 */
public class MyConsumer_Manual_Async_Submit {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092,hadoop103:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"bigdata-02132");
        //earliest,从最开头消费，第一次消费的时候才起作用；默认是latest，消费最新的
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        //关闭自动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("first"));

        try {
            while (true) {
                ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : poll) {
                    System.out.println(record);
                }
                //异步提交（一批提交，效率会高点）,提交offstet时，不会重读提交
                consumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        offsets.forEach(
                                (t,o)->{
                                    System.out.println("分区："+t+"；offset"+o.offset());
                                }
                        );
                    }
                });
            }
        } finally {
            consumer.close();
        }

    }
}
