package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk25_Transfrom_aggregateByKey{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 --Key - Value类型 	aggregateByKey
    // 将数据根据不同的规则进行分区内计算和分区间计算
    /*
      ggregateByKey算子是函数柯里化，存在两个参数列表
      1. 第一个参数列表中的参数表示初始值
      2. 第二个参数列表中含有两个参数
        2.1 第一个参数表示分区内的计算规则
        2.2 第二个参数表示分区间的计算规则
     */

    val rdd1 = sc.makeRDD(List(
      ("a",1),("a",2),("c",3),
      ("b",2),("c",5),("c",6),
    ),2)

    //todo 取出每个分区内相同key的最大值然后分区间相加
        //初始值的作用；为了和第一个值做比较。
    rdd1
      .aggregateByKey(0)(math.max(_,_),_+_)
      .collect()
      .foreach(println)
    rdd1
      .aggregateByKey(10)(math.max(_,_),_+_)
      .collect()
      .foreach(println)
    //todo 取出每个分区内相同key的值相加然后分区间相加
    rdd1
      .aggregateByKey(0)(_+_,_+_)
      .collect()
      .foreach(println)
    sc.stop()
  }

}
