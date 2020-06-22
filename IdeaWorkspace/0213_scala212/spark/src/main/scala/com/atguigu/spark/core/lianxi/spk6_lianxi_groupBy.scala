package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

object spk6_lianxi_groupBy {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo 小功能：将List("Hello", "hive", "hbase", "Hadoop")根据单词首写字母进行分组。
    val rdd = sc.makeRDD(List("Hello", "hive", "hbase", "Hadoop"))
    println(
      rdd
        .groupBy(_.substring(0,1))
        .collect()
        .mkString(",")
    )
   //todo 小功能：WordCount。
    println(
      rdd
        .groupBy(s=>s)
        .map(s=>(s._1,s._2.size))
        .collect()
        .mkString(",")
    )
    //todo 小功能：从服务器日志数据apache.log中获取每个时间段访问量。
    val rdd2 = sc.textFile("0213_scala212/input/apache.log")
    rdd2
      .groupBy(_.split(" ")(3).substring(11,13))
      .map(s=>(s._1,s._2.size))
      .collect()
      .foreach(println)

    sc.stop()
  }
}
