package com.atguigu.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming12_reduceByWindow {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Streaming")
    val ssc = new  StreamingContext(conf,Seconds(3))

    //todo 还没整明白
    val socketDS = ssc.socketTextStream("localhost",9999)
    socketDS
      .flatMap(_.split(" "))
      .map((_,1))
      .reduceByWindow((s:(String,Int),y:(String,Int))=>{(s._1,s._2+y._2) },Seconds(9),Seconds(6))
      .print()

    //启动采集器
    ssc.start()
    //等待采集器停止（阻塞）
    ssc.awaitTermination()

  }
}
