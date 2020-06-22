package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

object spk17_lianxi_aggregateByKey{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)
    val rdd =sc.makeRDD(
          List(
            ("a",1),("a",2),("c",3),
            ("b",4),("c",5),("c",6)
          ),2)

    //todo 分区内计算规则和分区间计算规则相同怎么办？（WordCount）
        //分区间规则相同，其实就是wordcount
        //初始值0，在计算时，（k，0）和第一个值做运算
    println(
      rdd
        .aggregateByKey(0)(_ + _, _ + _)
        .collect()
        .mkString(",")
    )


    sc.stop()
  }
}
