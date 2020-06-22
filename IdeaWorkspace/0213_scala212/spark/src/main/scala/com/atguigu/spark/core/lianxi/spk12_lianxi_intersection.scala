package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}


object spk12_lianxi_intersection{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
    //todo intersection  union subtract
        //todo 如果两个RDD数据类型不一致怎么办？
          //  类型必须一致，否则报错


    sc.stop()
  }
}
