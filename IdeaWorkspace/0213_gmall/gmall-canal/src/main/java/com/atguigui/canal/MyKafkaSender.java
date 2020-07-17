package com.atguigui.canal;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class MyKafkaSender {

    private  static KafkaProducer<String ,String> kafkaProducer=null;

    public static KafkaProducer<String,String> createrKafkaProducer(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop202:9092,hadoop203:9092,hadoop204:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);

        KafkaProducer<String, String> producer =null;
        try {
            producer= new KafkaProducer<>(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return producer;
    }
    public static void send(String topic,String msg){
        if(kafkaProducer==null){
            kafkaProducer=createrKafkaProducer();
        }
        kafkaProducer.send(new ProducerRecord<>(topic,msg));
    }
}
