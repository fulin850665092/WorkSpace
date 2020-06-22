package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}


object spk8_lianxi_sample {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
   //todo 思考一个问题：有啥用，抽奖吗？
      //做数据测试，检查数据的分散性，是否会造成数据倾斜

    sc.stop()
  }
}
