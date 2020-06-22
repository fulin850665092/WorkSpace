package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk31_Transfrom_cogroup{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 --Key - Value类型 cogroup
       // todo  连接之后，分组，并且分左右
        // co => connect=>同一个RDD的相同的key连接在一起
        // group=> 不同RDD中相同的key'放置在一个组中
        //底层类似于，笛卡尔积，性能差
    val rdd1 = sc.makeRDD(List(
      ("a",2),("b",5),("c",3),("a",3)
    ),2)
    val rdd2 = sc.makeRDD(List(
      ("a",1),("b",2)
    ),2)
    val rdd3 = rdd1.cogroup(rdd2)

      rdd3
        .collect()
        .foreach(println)

    sc.stop()
  }

}
