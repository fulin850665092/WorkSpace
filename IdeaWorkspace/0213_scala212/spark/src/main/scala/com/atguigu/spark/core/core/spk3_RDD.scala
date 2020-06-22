package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk3_RDD {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象）
    val sc : SparkContext = new SparkContext(sparkConf)

    //读取文件的分区和内存的分区是不一样的
      //读取文件时可以设定最小分区数，没设定使用默认值
        //源码：  def defaultMinPartitions: Int = math.min(defaultParallelism, 2)
          //defaultParallelism：[*]：当前电脑的最大核数：8
    //todo 读取文件时，分区计算方式？
          //spark 读取文件底层采用的是Hadoop的切片（分区）规则
    //   long goalSize = totalSize / (numSplits == 0 ? 1 : numSplits);
    //todo 分解文件数据时，数据如何分区？
        //hadoop读取文件时，是按照行的方式读取的，一行一行读
          //hadoop读取文件数据时，是按照偏移量读取的
    val rdd = sc.textFile("0213_scala212/input/word.txt")
    rdd.saveAsTextFile("0213_scala212/output/textFile")


    sc.stop()
  }
}
