package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

object spk22_lianxi_join{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo 	小功能：如果key存在不相等呢？
        //key不相等的话，不会进行join
    val rdd= sc.makeRDD(Array((1, "a"), (2, "b"), (3, "c"),(7,"d")))
    val rdd1= sc.makeRDD(Array((1, 4), (2, 5), (3, 6)))
    rdd.join(rdd1).collect().foreach(println)

    sc.stop()
  }

}
