package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk19_Transfrom_intersection {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
    //todo RDD 类型必须一致,分区可以不一致
    //todo RDD 转换算子 -- 双Value类型
    val rdd1 = sc.makeRDD(List(1,2,3,4),2)
    val rdd2 = sc.makeRDD(List(3,4,5,6),3)

    // todo  并集 union ，分区也合并(2+3)
    val rdd4 = rdd1.union(rdd2)
    // todo 交集  intersection 有shuffle，数据打乱后，保留最大的分区数量(3)
    val rdd3 = rdd1.intersection(rdd2)
    // todo  差集  subtract   有shuffle ，数据打乱后，以当前调用的rdd的分区（2）为主（rdd1）
    val rdd5 = rdd1.subtract(rdd2)
    println(
      rdd4
        .collect()
        .mkString(",")
    )

    println(
      rdd3
        .collect()
        .mkString(",")
    )
    println(
      rdd5
        .collect()
        .mkString(",")
      )


    sc.stop()
  }

}
