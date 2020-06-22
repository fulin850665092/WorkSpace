package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk14_Transfrom_distinct{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
      //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD转换算子 - distinct
      //将数据集中重复的数据去重
      //源码：map(x => (x, null)).reduceByKey((x, y) => x, numPartitions).map(_._1)
    val rdd = sc.makeRDD(List(1,3,3,2,2,4),3)
    //自定义去重
    val rdd2 = rdd.map((_,1)).reduceByKey(_+_).map(_._1)
    val rdd1=rdd.distinct()

    println(
      rdd1
        .collect()
        .mkString(",")
    )
    println("==========")
    println(
      rdd2
      .collect()
      .mkString(",")
    )


    sc.stop()
  }
}
