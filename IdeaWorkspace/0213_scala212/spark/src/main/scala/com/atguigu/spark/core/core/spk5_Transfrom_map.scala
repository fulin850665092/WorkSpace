package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk5_Transfrom_map {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
      //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
    val list = List(1,2,3,4)

    //todo RDD转换算子- map
      //分区不变转换
      //spark中的map算子底层调的是scala迭代器的map方法
      //map算子的分区等于之前的rdd的分区
    val rdd = sc.makeRDD(list,2)
//    rdd.saveAsTextFile("0213_scala212/output/trans")
    val rdd1 = rdd.map(s => {
      println("map2.....")
      s * 2 + "s"
    })
    rdd1.saveAsTextFile("0213_scala212/output/trans1")
    println(rdd1.collect().mkString(","))


    sc.stop()
  }
}
