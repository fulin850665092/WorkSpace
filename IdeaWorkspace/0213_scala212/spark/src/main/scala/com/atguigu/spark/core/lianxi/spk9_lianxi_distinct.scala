package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

object spk9_lianxi_distinct {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
   //todo 思考一个问题：有啥用，抽奖吗？
   val rdd = sc.makeRDD(List(
     1,2,3,4,1,2
   ),1)
    //todo 思考一个问题：如果不用该算子，你有什么办法实现数据去重？
    println(
      rdd
        .groupBy(s => s)
        .map(_._1)
        .collect()
        .mkString(",")
    )
    println(
      rdd
        .distinct()
        .collect()
        .mkString(",")
    )

    sc.stop()
  }
}
