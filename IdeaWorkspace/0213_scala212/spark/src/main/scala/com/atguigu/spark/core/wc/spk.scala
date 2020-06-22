package com.atguigu.spark.core.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object spk {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")

    // 创建Spark上下文环境对象（连接对象）
    val sc : SparkContext = new SparkContext(sparkConf)

    // 读取文件数据
    val fileRDD: RDD[String] = sc.textFile("0213_scala212/input/word.txt")

    // 将文件中的数据进行分词
    val wordRDD: RDD[String] = fileRDD.flatMap( _.split(" ") )

    // 转换数据结构 word => (word, 1)
    val word2OneRDD: RDD[(String, Int)] = wordRDD.map((_,1))

    // 将转换结构后的数据按照相同的单词进行分组
    val word2GroupRDD = word2OneRDD.groupBy(_._1)

    // 将转换结构后的数据按照相同的单词进行聚合
    val word2CountRDD = word2GroupRDD.map(s=>(s._1,s._2.size))

    // 将数据聚合结果采集到内存中
    val word2Count: Array[(String, Int)] = word2CountRDD.collect()

    // 打印结果
    word2Count.foreach(println)

    //关闭Spark连接
    sc.stop()

  }
}
