package com.atguigu.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object spk10_action_foreach{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD -- 行动算子： 	foreach
    val rdd = sc.makeRDD(List(1,2,3,4),2)

    rdd.collect().foreach(println)  //内存中循环打印
    println("==================")
    rdd.foreach(println) //分布式的循环打印
    println("==================")
    rdd.foreach(s=>{
      println(s)
    })
    sc.stop()
  }

}
