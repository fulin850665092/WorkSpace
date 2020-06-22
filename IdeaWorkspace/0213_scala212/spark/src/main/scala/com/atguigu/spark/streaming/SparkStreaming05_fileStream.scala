package com.atguigu.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

//todo 非真正的流式处理。是微批处理的准实时框架，SreamingContext 和Driver要同时启动，所以至少要两个线程local[*]
object SparkStreaming05_fileStream {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Streaming")
    val ssc = new  StreamingContext(conf,Seconds(3))

    val fileDS = ssc.textFileStream("0213_scala212/input")

    fileDS.flatMap(_.split(" "))
                      .map((_,1))
                      .reduceByKey(_+_)
                      .print()
    //启动采集器
    ssc.start()
    //等待采集器停止（阻塞）
    ssc.awaitTermination()

  }
}
