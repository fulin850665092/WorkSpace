package com.atguigu.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object spk1_action_reduce{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD -- 行动算子：	reduce
        //聚集RDD中的所有元素，先聚合分区内数据，再聚合分区间数据
    val rdd = sc.makeRDD(List(1,2,3,4))

    println(rdd.reduce(_ + _))

    sc.stop()
  }

}
