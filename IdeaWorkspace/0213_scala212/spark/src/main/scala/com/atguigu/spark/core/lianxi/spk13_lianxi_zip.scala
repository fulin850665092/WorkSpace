package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}


object spk13_lianxi_zip{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo 思考一个问题：如果两个RDD数据类型不一致怎么办？
        //可以拉链
     //todo  思考一个问题：如果两个RDD数据分区不一致怎么办？
          //会报错
    //todo  思考一个问题：如果两个RDD分区数据数量不一致怎么办？
          //会报错

    sc.stop()

  }
}
