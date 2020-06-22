package com.atguigu.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object spk3_action_count{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD -- 行动算子： 	count
        //返回RDD中元素的个数
    val rdd = sc.makeRDD(List(1,2,3,4))

    println(rdd.count())

    sc.stop()
  }

}
