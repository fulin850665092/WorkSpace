package com.atguigu.dw.gmall.realtime.app

import com.atguigu.dw.gmall.common.constant.GmallConstant
import com.atguigu.dw.gmall.realtime.util.MyKafkaUtil
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object OrderAppTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("OrderApp")
    val ssc = new StreamingContext(conf,Seconds(5))
    val kfkDS = MyKafkaUtil.getKafkaStream(ssc,GmallConstant.GMALL_ORDER_INFO)
    kfkDS.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
