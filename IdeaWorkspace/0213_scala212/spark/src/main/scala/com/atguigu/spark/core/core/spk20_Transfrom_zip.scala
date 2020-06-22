package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk20_Transfrom_zip {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 -- 双Value类型 	zip ，
    // 1:类型不一样可以拉链  2: 分区要一致  3：区内的元素数量要想等
    val rdd1 = sc.makeRDD(List(1,2,3,4))
    val rdd2 = sc.makeRDD(List(3,4,5,6))
    val rdd3 = sc.makeRDD(List("7","8","9","9"))
    val rdd4 = rdd2.zip(rdd1)
    val rdd5 = rdd2.zip(rdd3)
    println(
      rdd4
        .collect()
        .mkString(",")
    )
    println(
      rdd5
        .collect()
        .mkString(",")
    )

    sc.stop()
  }

}
