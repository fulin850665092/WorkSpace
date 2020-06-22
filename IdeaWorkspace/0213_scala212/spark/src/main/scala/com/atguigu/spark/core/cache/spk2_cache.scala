package com.atguigu.spark.core.cache

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object spk2_cache {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,4))

    val rdd1 = rdd.map(s => {
      println("num")
      s
    })

    //可以将rdd缓存到内存中
    val rdd2 = rdd1.cache()
    println(rdd2.toDebugString)
        //cache 是执行行动算子之后，才会增加缓存操作
    println(rdd2.collect().mkString(","))
    println(rdd2.toDebugString)
    sc.stop()
  }
}
