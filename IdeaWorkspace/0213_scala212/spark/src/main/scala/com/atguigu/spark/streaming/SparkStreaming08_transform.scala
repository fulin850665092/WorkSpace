package com.atguigu.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

//todo transform,转换，其实也就是对DStream中的RDD应用转换。
object SparkStreaming08_transform {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Streaming")
    val ssc = new  StreamingContext(conf,Seconds(3))

    val socketDS = ssc.socketTextStream("localhost",9999)
    val newDS = socketDS.transform(
      //todo Driver 端执行,会执行（N次），周期性执行
      rdd=> {
        rdd.map(
          //todo Executor 端执行，会执行（N次），周期性执行
          s => {
            s * 2
          }
        )
      }
    )
    //todo Driver 端执行,执行（1次）
//    newDS.map(s=>{
//      s*2
//    })
    newDS.print()
    //启动采集器
    ssc.start()
    //等待采集器停止（阻塞）
    ssc.awaitTermination()

  }
}
