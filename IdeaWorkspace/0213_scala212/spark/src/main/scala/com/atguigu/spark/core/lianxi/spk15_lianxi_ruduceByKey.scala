package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

object spk15_lianxi_ruduceByKey{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,5,2,4,6,4,5),3)

    //todo	小功能：WordCount
    println(
      rdd
      .map((_, 1))
      .reduceByKey(_ + _)
      .collect()
      .mkString(",")
    )

    sc.stop()
  }
}
