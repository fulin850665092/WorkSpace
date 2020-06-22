package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

object spk16_lianxi_groupByKey{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo reduceByKey和groupByKey的区别？
        //reduceBykey ：是预聚合，有shuffle过程，再将数据写入file中，最后在读入内存中直接的得到结果，效率高
        //groupByKey:不会预聚合，有shuffle过程，将数据一个一个写入file中，分区内数据全部写完后，在聚合，耗内存，效率低
    val rdd = sc.makeRDD(List(1,2,3,5,2,4,6,4,5),3)

    //todo	小功能：WordCount
    println(
      rdd
      .map((_, 1))
      .reduceByKey(_ + _)
      .collect()
      .mkString(",")
    )
    println(
      rdd
        .map((_, 1))
        .groupByKey()
        .map(s=>(s._1,s._2.size))
        .collect()
        .mkString(",")
    )
    sc.stop()
  }
}
