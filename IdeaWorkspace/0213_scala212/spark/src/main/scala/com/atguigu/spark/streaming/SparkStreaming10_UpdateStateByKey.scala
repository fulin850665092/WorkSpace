package com.atguigu.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

//todo UpdateStateByKey 将spark每个周期采集的数据保存起来，然后和后续的数据进行聚合
object SparkStreaming10_UpdateStateByKey {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Streaming")
    val ssc = new  StreamingContext(conf,Seconds(3))

    ssc.checkpoint("0213_scala212/output/UpdateStateByKey")

    val socketDS1 = ssc.socketTextStream("localhost",9999)

    //reduceBykey 方法是无状态的，而我们需要的是有状态的数据操作
    socketDS1
      .flatMap(_.split(" "))
      .map((_,1L))
      //todo 第一个参数表示，相同key的value集合（就是采集到的数据，满足key的value集合）
      //todo 第二个参数表示，相同key的缓冲区的数据，有可能为空
      .updateStateByKey((seq:Seq[Long],buffer:Option[Long])=>{
        val newBuffervakue = buffer.getOrElse(0L) +seq.sum
        Option(newBuffervakue)
      })

//    .reduceByKey(_+_)
      .print()

    //启动采集器
    ssc.start()
    //等待采集器停止（阻塞）
    ssc.awaitTermination()

  }
}
