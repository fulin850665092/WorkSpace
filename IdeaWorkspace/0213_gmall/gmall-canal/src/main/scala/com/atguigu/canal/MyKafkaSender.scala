package com.atguigu.canal

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer
import java.util.Properties


object MyKafkaSender {
  var kafkaProducer:KafkaProducer[String, String]=_
  def createrKafkaProducer: KafkaProducer[String, String] = {

    val properties = new Properties
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop202:9092,hadoop203:9092,hadoop204:9092")
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    val producer = new KafkaProducer[String, String](properties)
    producer
  }

  def send(topic: String, msg: String): Unit = {
    if (kafkaProducer == null) kafkaProducer = createrKafkaProducer
    kafkaProducer.send(new ProducerRecord[String, String](topic, msg))
  }
}

