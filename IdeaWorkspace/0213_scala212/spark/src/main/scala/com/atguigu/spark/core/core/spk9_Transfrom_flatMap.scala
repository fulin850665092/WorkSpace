package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk9_Transfrom_flatMap {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
      //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
    val list = List(
      List(1,2,3,4),
      List(5,6,7)
    )

    //todo RDD转换算子 - flatMap
        //将处理的数据进行扁平化后再进行映射处理，所以算子也称之为扁平映射
    val rdd = sc.makeRDD(list,2)

    val rdd1 = rdd.flatMap(s=>s)

    println(
      rdd1
        .collect()
        .mkString(",")
    )

    sc.stop()
  }
}
