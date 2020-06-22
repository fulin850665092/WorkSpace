package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk29_Transfrom_join{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 --Key - Value类型 	join
       // todo 在类型为(K,V)和(K,W)的RDD上调用，返回一个相同key对应的所有元素连接在一起的(K,(V,W))的RDD
        //底层类似于，笛卡尔积，性能差
    val rdd1 = sc.makeRDD(List(
      ("a",2),("b",5),("a",3)
    ),2)
    val rdd2 = sc.makeRDD(List(
      ("a",1),("b",2)
    ),2)
    val rdd3 = rdd1.join(rdd2)
    println(
      rdd3
      .collect()
      .mkString(",")
    )





    sc.stop()
  }

}
