package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk12_Transfrom_filter{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
      //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD转换算子 - filter
      //过滤数据
        //极限情况下，可能会出现数据倾斜
    val rdd = sc.makeRDD(List(1,3,2,4),3)

    val rdd1=rdd.filter(_%2==0)

      rdd1
        .collect()
        .foreach(println)

    sc.stop()
  }
}
