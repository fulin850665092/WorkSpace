package com.atguigu.summer.framework.core

import java.util.Properties

import com.atguigu.summer.framework.util.{AppUtil, PropertiesUtil}
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.codehaus.jackson.map.ser.std.StdKeySerializers.StringKeySerializer

trait TDao {
    /**
      * todo sc读取文件信息
      * @param path
      * @return
      */
    def   readFile(path:String)={
        AppUtil.SparkContext().textFile(path)
    }


    /**
      * todo 向kakfa中消费数据
      * @return
      */
    def readKafka()={
        //从配置文件中，获取配置信息
        val brokerList = PropertiesUtil.getValue("kafka.broker.list")
        val groupid = PropertiesUtil.getValue("kafka.group.id")
        val topic = PropertiesUtil.getValue("kafka.topic")
        //kafka集群连接属性封装
        val kafkaParams = Map[String,Object](
                                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG->brokerList,
                                        ConsumerConfig.GROUP_ID_CONFIG->groupid,
                                        "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
                                        "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer")
        //createDirectStream[K, V](ssc: StreamingContext,
                                //      locationStrategy: LocationStrategy,
                                //      consumerStrategy: ConsumerStrategy[K, V]
                                //    )
        val kafkaDStream = KafkaUtils.createDirectStream(
            AppUtil.StreamingContext(),
            LocationStrategies.PreferConsistent,
            ConsumerStrategies.Subscribe[String,String](Set(topic), kafkaParams)
        )
        //只取Value
        kafkaDStream.map(record => record.value())
    }

    /**
      *  todo 向kafka中生产数据
      * @param datas
      */
    def  writeKafka( implicit datas:Seq[String] )={
        //todo 从配置文件中，获取配置信息
        val brokerList = PropertiesUtil.getValue("kafka.broker.list")
        val groupid = PropertiesUtil.getValue("kafka.group.id")
        val topic = PropertiesUtil.getValue("kafka.topic")
        //kafka连接属性封装
        val properties = new Properties
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,brokerList)
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

        val kafkaProduce = new  KafkaProducer[String,String](properties)
        //循环发送数据
        while (true){
            for (line <- datas) {
                kafkaProduce.send(new ProducerRecord[String,String](topic,line))
            }
            Thread.sleep(2000)
        }
    }
}
