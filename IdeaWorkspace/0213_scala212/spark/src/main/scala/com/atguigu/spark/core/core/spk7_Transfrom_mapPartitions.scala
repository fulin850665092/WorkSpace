package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk7_Transfrom_mapPartitions {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
      //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
    val list = List(1,2,3,4)

    //todo RDD转换算子 - mapPartitions
    //以分区为单位处理
      //数据处理，分区不变
    val rdd = sc.makeRDD(list,2)

    val rdd1 = rdd.mapPartitions(datas => {
      datas.filter(_%2==0)
    })
//    rdd1.saveAsTextFile("0213_scala212/output/trans7")
    println(rdd1.collect().mkString(","))
    println(rdd.mapPartitions(datas => {
      datas.map(_ * 2 + "s")
    }).collect().mkString(","))

    sc.stop()
  }
}
