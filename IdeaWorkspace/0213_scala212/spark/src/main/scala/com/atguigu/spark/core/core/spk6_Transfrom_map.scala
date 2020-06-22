package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk6_Transfrom_map {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
      //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
    val list = List(1,2,3,4)

    //todo RDD转换算子 - map
        //因为数据有多个分区，所以可以并行计算
        //同一个分区的数据是迭代执行
    val rdd = sc.makeRDD(list,2)

    val rdd1 = rdd.map(s => {
      println("s="+s)
      s * 2 + "s"
    })
    val rdd2 = rdd1.map(s => {
      println("string=" + s)
      s * 2
    })

    rdd2.saveAsTextFile("0213_scala212/output/trans6")
    println(rdd2.collect().mkString(","))


    sc.stop()
  }
}
