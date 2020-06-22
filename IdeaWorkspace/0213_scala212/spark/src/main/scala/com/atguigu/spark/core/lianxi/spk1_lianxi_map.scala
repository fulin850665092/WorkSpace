package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 从服务器日志数据apache.log中获取用户请求URL资源路径
  */
object spk1_lianxi_map {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)

    val rdd = sc.textFile("0213_scala212/input/apache.log")

    println(
      rdd
        .map(_.split(" ")(6))
        .collect()
        .foreach(println)
    )

    sc.stop()
  }
}
