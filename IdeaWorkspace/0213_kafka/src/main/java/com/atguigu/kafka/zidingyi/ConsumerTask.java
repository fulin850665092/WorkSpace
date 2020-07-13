package com.atguigu.kafka.zidingyi;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * 如果实现Runnable接口，出现异常，需要在run方法进行捕获
 */
class ConsumerTask implements Runnable{

    private ConsumerRecord<String, String> record;

    public ConsumerTask(ConsumerRecord<String, String> record) {
        this.record = record;
    }

    @Override
    public void run() {
        JSONObject json= JSONObject.parseObject(record.value());
        System.out.println("消费的消息是"+json.toJSONString()+"，消息的分区为："+record.partition()+"，消息的offse为："+record.offset());
    }
}