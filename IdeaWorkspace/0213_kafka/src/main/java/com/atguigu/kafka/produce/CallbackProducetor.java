package com.atguigu.kafka.produce;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class CallbackProducetor {
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
        //创建KafkaProducer对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        //调用send方法
        for (int i = 0; i <1000 ; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("first","message"+i);
            kafkaProducer.send(producerRecord, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e==null){
                        System.out.println("success:"+recordMetadata.topic()+"-"+recordMetadata.partition()
                        +"-"+recordMetadata.offset());
                    }else {
                        //失败次数耗尽，才继续返回异常
                        e.printStackTrace();
                    }
                }
            });
        }

        kafkaProducer.close();
    }

}
