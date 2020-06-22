package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

object spk18_lianxi_foldByKey{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)
    val rdd =sc.makeRDD(
          List(
            ("a",1),("a",2),("c",3),
            ("b",4),("c",5),("c",6)
          ),2)

    //todo  分区内计算规则和分区间计算规则相同，用foldByKey
    println(
      rdd
        .foldByKey(0)(_ + _)
        .collect()
        .mkString(",")
    )


    sc.stop()
  }
}
