package com.atguigu.spark.core.wc

import org.apache.spark.{SparkConf, SparkContext}

object spk4 {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象）
    val sc : SparkContext = new SparkContext(sparkConf)

    sc
      .textFile("data/word.txt")
      .flatMap(_.split(" ").map((_,1)))
      .reduceByKey(_+_)
      .foreach(println)
    //关闭Spark连接
    sc.stop()

  }
}
