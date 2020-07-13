package com.atguigu.kafka.zidingyi;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 定义一个生产者，将消息发送出去
 */
public class MyProducer {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //step1 配置参数，这些跟优化kafka性能有关系
        Properties props=new Properties();

        //1 连接broker
        props.put("bootstrap.servers","hadoop01:9092,hadoop02:9092,hadoop03:9092");

        //2 key和value序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //3 acks
        // -1 代表所有处于isr列表中的follower partition都会同步写入消息成功
        // 0 代表消息只要发送出去就行，其他不管
        // 1 代表发送消息到leader partition写入成功就可以
        props.put("acks","-1");

        //4 重试次数
        props.put("retries",3);//大部分问题，设置这个就可以解决，生产环境可以设置多些 5-10次

        // 5 隔多久重试一次
        props.put("retry.backoff.ms",2000);

        //6 如果要提升kafka的吞吐量，可以指定压缩类型
        props.put("compression.type","none");

        //7 缓冲区大小，默认是32M
        props.put("buffer.size",33554432);

        //8 一个批次batch的大小，默认是16k，需要根据一条消息的大小去调整
        props.put("batch.size",323840);//设置为32k

        //9 如果一个batch没满，达到如下的时间也会发送出去
        props.put("linger.ms",200);

        //10 一条消息最大的大小,默认是1M，生产环境中一般会修改变大，否则会报错
        props.put("max.request.size",1048576);

        //11 一条消息发送出去后，多久还没收到响应，就认为是超时
        props.put("request.timeout.ms",5000);

        //12 todo 使用自定义分区器
        props.put("partitioner.class","com.boe.partitioner.MyPartitioner");


        //step2 创建生产者对象
        KafkaProducer<String,String> producer=new KafkaProducer<String, String>(props);

        //step3 使用消息的封装形式
        //自定义分区测试用的，可以看到自定了key，以下每条消息发送两次
//        ProducerRecord<String,String> record=new ProducerRecord<String,String>("country","china","{'name':'china','population','14'}");
//        ProducerRecord<String,String> record=new ProducerRecord<String,String>("country","usa","{'name':'usa','population','3'}");
        ProducerRecord<String,String> record=new ProducerRecord<String,String>("country","korea","{'name':'korea','population','1'}");


        //step4 调用生产者对象的send方法发送消息，有异步和同步两种选择

        //1 异步发送，一般使用异步
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception==null){
                    System.out.println("消息发送到分区"+metadata.partition()+"成功");
                }else{
                    System.out.println("消息发送失败");
                }
            }
        });

        Thread.sleep(10*1000);

        //2 同步发送，需要等待一条消息发送完成，才能发送下一条消息
        //RecordMetadata recordMetadata = producer.send(record).get();
        //System.out.println("发送到的分区是："+recordMetadata.partition());

        //step5 关闭连接
        producer.close();
    }

}