package com.atguigu.spark.core.core

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object spk22_Transfrom_ZDY_partitionBy{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 --Key--Value类型 分区器

    val rdd = sc.makeRDD(List(
            ("nba","xxxx"),
            ("cba","xxxx"),
            ("nba","yyyy"),
            ("wnba","yyyy"),

          ),2)
    //实现自定义分区
    val rdd2 = rdd.partitionBy(new myPartitioner(2))

    rdd2.mapPartitionsWithIndex((index, dt) => {
            dt.map((index, _))
          })
        .collect()
        .foreach(println)

    sc.stop()
  }
  //自定义分区器
      //1：继承myPartition
      //2。重写方法
  class myPartitioner (partitions: Int) extends Partitioner{
    //设定分区数
    override def numPartitions: Int =  partitions

    //根据key计算数据所在的分区索引
    override def getPartition(key: Any): Int = {
      if (key.isInstanceOf[String]) {
        val keystr = key.asInstanceOf[String]
        if (keystr == "nba") 0  else  1
      } else 1
    }
  }
}
