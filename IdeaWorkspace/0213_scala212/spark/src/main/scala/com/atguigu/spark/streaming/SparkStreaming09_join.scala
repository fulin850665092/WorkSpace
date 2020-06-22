package com.atguigu.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

//todo join 对当前批次的两个流中各自的RDD进行join，与两个RDD的join效果相同
object SparkStreaming09_join {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Streaming")
    val ssc = new  StreamingContext(conf,Seconds(3))

    val socketDS1 = ssc.socketTextStream("localhost",9999)
    val socketDS2 = ssc.socketTextStream("localhost",8888)
    val ds1 = socketDS1.map((_,1))
    val ds2 = socketDS2.map((_,1))
    //将多个ds，join在一起
    val joinDs = ds1.join(ds1)
    joinDs.print()
    //启动采集器
    ssc.start()
    //等待采集器停止（阻塞）
    ssc.awaitTermination()

  }
}
