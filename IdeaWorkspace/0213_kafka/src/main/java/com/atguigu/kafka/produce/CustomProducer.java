package com.atguigu.kafka.produce;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class CustomProducer {
    //KafkaProducer：需要创建一个生产者对象，用来发送数据
    //ProducerConfig：获取所需的一系列配置参数
    //ProducerRecord：每条数据都要封装成一个ProducerRecord对象
    public static void main(String[] args) {

        Properties properties = new Properties();
        //必须得配置
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092,hadoop103:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.RETRIES_CONFIG,1);
        properties.put(ProducerConfig.LINGER_MS_CONFIG,100);
        //创建KafkaProducer对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        //调用send方法
        for (int i = 0; i <1000 ; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("first","message"+i,"message"+i);
            Future<RecordMetadata> send = kafkaProducer.send(producerRecord);
        }
        //所有的消息都收到ack才关闭（异步通信要注意的）
       kafkaProducer.close();
    }

}
