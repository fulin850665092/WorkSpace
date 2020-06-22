package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk15_Transfrom_coalesce{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
      //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD转换算子 - coalesce
      //根据数据量缩减分区，用于大数据集过滤后，提高小数据集的执行效率
        //当spark程序中，存在过多的小任务的时候，可以通过coalesce方法，
        // 收缩合并分区，减少分区的个数，减小任务调度成本
    val rdd = sc.makeRDD(List(1,2,3,4,5,6),3)
    //todo 默认情况下，单纯的区和区合并，数据不均衡
    val rdd1=rdd.coalesce(2)
    val rdd2 = rdd1.mapPartitionsWithIndex((index, dt) => {
      dt.map((index, _))
    })

    //todo 开启shuffer，会打乱数据，重新均衡组合
    val rdd3=rdd.coalesce(2,true)
    val rdd4 = rdd3.mapPartitionsWithIndex((index, dt) => {
      dt.map((index, _))
    })
    println("默认情况下：============")
    println(
      rdd1
        .collect()
        .mkString(",")
    )
    println(
      rdd2
        .collect()
        .mkString(",")
    )
    println("开启buffer之后：============")
    println(
      rdd3
        .collect()
        .mkString(",")
    )
    println(
      rdd4
        .collect()
        .mkString(",")
    )

    sc.stop()
  }
}
