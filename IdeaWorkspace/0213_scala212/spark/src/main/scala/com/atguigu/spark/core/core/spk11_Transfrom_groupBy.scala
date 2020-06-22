package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk11_Transfrom_groupBy{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
      //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
    val list = List(1,2,3,4)


    //todo RDD转换算子 - groupBy
      //将同一个分区的数据直接转换为相同类型的内存数组进行处理，分区不变
    val rdd = sc.makeRDD(list,3)
    //groupBy：属于 shuffer过程，可以重新设置分区
    val rdd1=rdd.groupBy((s:Int) =>{ s%2 },2)
    println(rdd1.glom().collect().length)
      rdd1
        .collect()
        .foreach(println)

    sc.stop()
  }
}
