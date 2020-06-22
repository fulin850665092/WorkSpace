package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk23_Transfrom_reduceByKey {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 --Key-Value类型 	groupByKey
    // 可以将数据按照相同的Key对Value进行：分组+聚合   按key分组，按value集合

    val rdd1 = sc.makeRDD(List(
      ("hello",1),
      ("hello",2),
      ("hadoop",4)
    ))
    // todo  spark中 所有的bykey算子都需要通过kv类型的RDD进行调用

    rdd1
      .reduceByKey(_+_)
      .collect()
      .foreach(println)

    sc.stop()
  }

}
