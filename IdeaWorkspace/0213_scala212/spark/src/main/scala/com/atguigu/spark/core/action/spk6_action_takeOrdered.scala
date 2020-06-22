package com.atguigu.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object spk6_action_takeOrdered{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD -- 行动算子： 	takeOrdered
        //返回该RDD排序后的前n个元素组成的数组
    val rdd = sc.makeRDD(List(4,1,3,2))
      //先排序，再去前2个
      rdd.takeOrdered(2).foreach(println)

    sc.stop()
  }

}
