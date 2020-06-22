package com.atguigu.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

//todo UpdateStateByKey 将spark每个周期采集的数据保存起来，然后和后续的数据进行聚合
object SparkStreaming11_window {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Streaming")
    val ssc = new  StreamingContext(conf,Seconds(3))


    val socketDS = ssc.socketTextStream("localhost",9999)
    socketDS
      .flatMap(_.split(" "))
      .map((_,1))
      //todo 窗口的范围应该是：采集周期的整数倍，
      // 默认的滑动的步长：一个采集周期
      // 窗口的计算的周期：等同于窗口滑动的步长（幅度）
      // 窗口的滑动步长应该是：采集周期的整数倍
      .window(Seconds(9),Seconds(6))
      .reduceByKey(_+_)
      .print()

    //启动采集器
    ssc.start()
    //等待采集器停止（阻塞）
    ssc.awaitTermination()

  }
}
