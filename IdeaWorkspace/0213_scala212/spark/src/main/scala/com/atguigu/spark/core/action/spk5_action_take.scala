package com.atguigu.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object spk5_action_take{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD -- 行动算子： 	take
        //返回一个由RDD的前n个元素组成的数组
    val rdd = sc.makeRDD(List(1,2,3,4))

     rdd.take(3).foreach(println)

    sc.stop()
  }

}
