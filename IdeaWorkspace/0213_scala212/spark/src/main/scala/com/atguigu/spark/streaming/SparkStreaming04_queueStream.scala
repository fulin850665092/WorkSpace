package com.atguigu.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object SparkStreaming04_queueStream {
  def main(args: Array[String]): Unit = {
    //环境
    val conf = new SparkConf().setAppName("streaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(3))
    //逻辑
    val queue = new mutable.Queue[RDD[String]]()
    //从socket获取数据，一行一行获取的
    val queueDS = ssc.queueStream(queue)
    queueDS
      .flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()


    //启动采集器
    ssc.start()
    for (elem <- 1 to 5) {
      val rdd = ssc.sparkContext.makeRDD(List(elem.toString))
      queue.enqueue(rdd)
      Thread.sleep(1000)
    }

    //等待采集器的结束，开启阻塞状态
    ssc.awaitTermination()
  }
}
