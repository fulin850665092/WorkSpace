package com.atguigu.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

//todo UpdateStateByKey 将spark每个周期采集的数据保存起来，然后和后续的数据进行聚合
object SparkStreaming13_reduceByKeyAndWindow {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Streaming")
    val ssc = new  StreamingContext(conf,Seconds(3))


    val socketDS = ssc.socketTextStream("localhost",9999)
    socketDS
      .flatMap(_.split(" "))
      .map((_,1))
      .reduceByKeyAndWindow((x:Int,y:Int)=>x+y,Seconds(9),Seconds(6))
      .print()

    //启动采集器
    ssc.start()
    //等待采集器停止（阻塞）
    ssc.awaitTermination()

  }
}
