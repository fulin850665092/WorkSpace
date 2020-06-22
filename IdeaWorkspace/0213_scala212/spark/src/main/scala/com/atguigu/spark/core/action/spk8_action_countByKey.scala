package com.atguigu.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object spk8_action_countByKey{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD -- 行动算子： 	countByKey  ,countByValue

    val rdd = sc.makeRDD(List("a","b","a","b"),2)
    //可以用来实现wordcount的一直方式
    println(rdd.map((_, 1)).countByKey())
    //可以用来实现wordcount的一直方式
    val rdd2 = rdd.countByValue()
    println(rdd2)

    sc.stop()
  }

}
