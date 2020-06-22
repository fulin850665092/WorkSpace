package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk1_RDD {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象）
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo 从内存中创建RDD
    val list = List(1,2,3,4,5)
    val RDD1 = sc.parallelize(list)
    println(RDD1.collect().mkString(","))

    val RDD2 = sc.makeRDD(list)
    println(RDD2.collect().mkString(","))

    //todo 从文件系统中读取规则和hadoop读取规则一样（按照行读取）
    //todo 从文件夹中获取RDD
    val RDD3 = sc.textFile("0213_scala212/input")
    println(RDD3.collect().mkString(","))

    //todo 从匹配多个文件获取RDD
    val RDD4 = sc.textFile("0213_scala212/input/word*.txt")
    println(RDD4.collect().mkString(","))

    //todo 从文件中获取RDD
    val RDD5 = sc.textFile("0213_scala212/input/word.txt")
    println(RDD5.collect().mkString(","))

   //todo 还可以从其他系统读取RDD

    sc.stop()
  }
}
