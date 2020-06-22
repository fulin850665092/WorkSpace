package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk28_Transfrom_sortByKey{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 --Key - Value类型 	sortByKey
       //todo  在一个(K,V)的RDD上调用，K必须实现Ordered接口，返回一个按照key进行排序的
    val rdd1 = sc.makeRDD(List(
      ("a",1),("a",2),("c",3),
      ("b",2),("c",5),("c",6),
    ),2)

    println(
      rdd1
      .sortByKey()
      .collect()
      .mkString(",")
    )
    println(
      rdd1
        .sortByKey(false)
        .collect()
        .mkString(",")
    )




    sc.stop()
  }

}
