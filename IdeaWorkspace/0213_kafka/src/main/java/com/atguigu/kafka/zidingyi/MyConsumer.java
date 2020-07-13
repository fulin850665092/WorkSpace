package com.atguigu.kafka.zidingyi;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * 自定义一个消费者，从指定的topic消费数据
 */
public class MyConsumer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //step1 配置消费者参数，也跟kafka性能有关
        Properties props=new Properties();

        //1 连接broker
        props.put("bootstrap.servers","hadoop01:9092,hadoop02:9092,hadoop03:9092");

        //2 指定key和value的反序列化
        //还需要指定消费组id，否则报错
        props.put("group.id","test");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //3 消费者给coordinator发送心跳的时间间隔
        props.put("heartbeat.interval.ms",1000);

        //4 coordinator认为多久没接受到心跳，就认为超时
        props.put("session.timout.ms",10*1000);

        //5 隔多久执行一次poll
        props.put("max.poll.interval.ms",10*1000);

        //6 一次poll返回多少条record，默认是500条
        props.put("max.poll.records",1000);

        //7 不要回收socket连接
        //consumer跟broker的socket连接如果空闲超过了一定的时间，此时就会自动回收连接，
        //但是下次消费就要重新建立socket连接，这个建议设置为-1，不要去回收连接
        props.put("connection.max.idle.ms",-1);

        //8 设置自动提交offset
        props.put("enable.auto.commit","true");//注意kafka版本,1.0.x是这么写

        //9 多久自动提交offset
        props.put("auto.commit.interval.ms",1000);

        //10 设置consumer重启后，从分区最新的offset读取
        //latest:如果分区下有提交的offset，从这个offset开始读取，否则从最新的数据开始读取
        //earliest:如果分区下有提交的offset，从这个offset开始读取，否则从头开始读取
        //none:如果分区下有提交的offset，从这个offset开始读取，只要有一个分区没有提交的offset，就报错
        props.put("auto.offset.reset","latest");

        //step2 创建一个消费者对象
        KafkaConsumer<String,String> consumer=new KafkaConsumer<String, String>(props);


        //step3 订阅主题
        consumer.subscribe(Arrays.asList("topicA"));

        //创建线程池，小池子大队列，只有核心线程，没有临时线程，工作队列是个阻塞式队列
        ExecutorService threadPool= Executors.newFixedThreadPool(5);


        //step4 不断消费数据，并对数据进行处理

        try {
            while(true){
                //超时时间是3s
                //新版本的kafka，这个poll方法将干很多事情
                //如监听这个消费者跟多个topic的分区所在broker的通信，如有新的数据就会拉取过来，缓存数据、内存里更新offset
                ConsumerRecords<String, String> consumerRecords = consumer.poll(3000);
                for(ConsumerRecord<String, String> record:consumerRecords){

                    //1 写法1
                    //如果value是json格式，将其转换成JSON对象
                    //JSONObject json=JSONObject.parseObject(record.value());
                    //System.out.println("消费的消息是"+json.toJSONString()+"，name为："+json.getString("name"));

                    //2 写法2 可以放到线程池去消费
                    //实现Runnable接口
                    threadPool.submit(new ConsumerTask(record));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("消费消息失败");
            consumer.close();
        }

    }

}

