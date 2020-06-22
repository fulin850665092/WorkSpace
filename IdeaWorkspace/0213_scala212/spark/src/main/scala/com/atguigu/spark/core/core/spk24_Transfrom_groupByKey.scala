package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk24_Transfrom_groupByKey {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 --Key - Value类型 	groupByKey
    // 将分区的数据直接转换为相同类型的内存数组进行后续处理

    val rdd1 = sc.makeRDD(List(
      ("hello",1),
      ("hello",2),
      ("hadoop",4)
    ))

    rdd1
      .groupByKey()
      .mapValues(_.sum)
      .collect()
      .foreach(println)
    sc.stop()
  }

}
