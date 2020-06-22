package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 获取每个数据分区的最大值
  */
object spk2_lianxi_mapPartitions {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)

    println(
      sc
        .makeRDD(List(1, 5, 7, 4), 2)
        .mapPartitions(s=>List(s.max).iterator)
        .collect()
        .mkString(",")
    )
    sc.stop()
  }
}
