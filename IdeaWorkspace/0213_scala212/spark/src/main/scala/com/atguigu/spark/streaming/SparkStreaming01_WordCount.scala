package com.atguigu.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming01_WordCount {
  def main(args: Array[String]): Unit = {
    //环境
    val conf = new SparkConf().setAppName("streaming").setMaster("local[*]")
    val ssc = new StreamingContext(conf,Seconds(3))
    //逻辑
    //从socket获取数据，一行一行获取的
    val rds = ssc.socketTextStream("localhost",999)
    rds
      .flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()

    //关闭
    //Driver 程序执行streaming处理过程中不能结束
    ///采集器正常情况下启动后不能停止，除非特殊情况
//    ssc.stop()
    //启动采集器
    ssc.start()
    //等待采集器的结束，开启阻塞状态
    ssc.awaitTermination()
  }
}
