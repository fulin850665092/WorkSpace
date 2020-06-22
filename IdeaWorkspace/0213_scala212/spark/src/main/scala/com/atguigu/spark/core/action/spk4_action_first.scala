package com.atguigu.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object spk4_action_first{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD -- 行动算子： 	first
        //返回RDD中的第一个元素
    val rdd = sc.makeRDD(List(1,2,3,4))

    println(rdd.first())

    sc.stop()
  }

}
