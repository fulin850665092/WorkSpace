package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk30_Transfrom_leftOuterJoin{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 --Key - Value类型 	leftOuterJoin (左边为主) rightOuterJoin（右边为主）
       // todo 类似于SQL语句的外连接
        //todo 底层类似于，笛卡尔积，性能差
    val rdd1 = sc.makeRDD(List(
      ("a",2),("b",5),("c",3)
    ),2)
    val rdd2 = sc.makeRDD(List(
      ("a",1),("b",2),("a",3)
    ),2)
    val rdd3 = rdd1.leftOuterJoin(rdd2)

    val rdd4 = rdd1.rightOuterJoin(rdd2)

      rdd3
      .collect()
        .foreach(println)

    println("=============")

      rdd4
        .collect()
        .foreach(println)







    sc.stop()
  }

}
