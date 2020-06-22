package com.atguigu.kafka.produce;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class Produce {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        //StringSerializer.class也行
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        //设置压缩
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        for (int i = 0; i <10000; i++) {
            Future<RecordMetadata> recordMetadataFuture = producer.send(new ProducerRecord<String, String>("first", "mess" + i, i + ""), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e==null){
                        System.out.println("offset："+recordMetadata.offset()+"分区："+recordMetadata.partition()+"成功了！");
                    }
                }
            });
        }
        producer.close();
    }
}
