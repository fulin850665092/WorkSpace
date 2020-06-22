package com.atguigu.spark.core.cache

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object spk1_cache {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,4))

    val rdd1 = rdd.map(s => {
      println("num")
      s
    })

    //rdd 调用一次行动算子，就是一个job ，每个job都会从头读取数据
    println(rdd1.collect().mkString(","))
    rdd1.foreach(println)
    //可以将rdd 临时的缓存起来，提高效率
    val rdd2 = rdd1.persist(StorageLevel.MEMORY_ONLY)
    println("================")
    rdd2.foreach(println)
    sc.stop()
  }
}
