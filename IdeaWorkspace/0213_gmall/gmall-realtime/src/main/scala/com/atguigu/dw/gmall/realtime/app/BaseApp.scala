package com.atguigu.dw.gmall.realtime.app

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

trait BaseApp {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("OrderApp")
    val ssc = new StreamingContext(conf,Seconds(5))
    run(ssc)
    ssc.start()
    ssc.awaitTermination()
  }

  def  run(ssc:StreamingContext)
}
