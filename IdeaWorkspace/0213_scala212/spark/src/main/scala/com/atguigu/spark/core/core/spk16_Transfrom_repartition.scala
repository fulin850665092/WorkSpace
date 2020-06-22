package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk16_Transfrom_repartition{
  def main(args: Array[String]): Unit = {

    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 	repartition
    //该操作内部其实执行的是coalesce操作，参数shuffle的默认值为true。
    // 无论是将分区数多的RDD转换为分区数少的RDD，还是将分区数少的RDD转换为分区数多的RDD，
    // repartition操作都可以完成，因为无论如何都会经shuffle过程。
    val rdd = sc.makeRDD(List(1,2,3,4,5,6),3)
    val rdd1 = rdd.repartition(4)

    println(
      rdd1
        .collect()
        .mkString(",")
    )

    rdd1
      .mapPartitionsWithIndex((index, dt) => {
          dt.map((index, _))
        })
      .collect().foreach(println)



    sc.stop()
  }
}
