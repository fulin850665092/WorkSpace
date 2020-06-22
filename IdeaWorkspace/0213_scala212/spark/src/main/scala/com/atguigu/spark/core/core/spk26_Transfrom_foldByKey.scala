package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk26_Transfrom_foldByKey{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 --Key - Value类型 	foldByKey
    //如果aggregateByKey算子中 分区内和分区外的计算规则可以用foldByKey代替

    val rdd1 = sc.makeRDD(List(
      ("a",1),("a",2),("c",3),
      ("b",2),("c",5),("c",6),
    ),2)

    //todo 取出每个分区内相同key的值相加然后分区间相加
        //初始值的作用；为了和第一个值做比较。
    rdd1
      .foldByKey(0)(_+_)
      .collect()
      .foreach(println)



    sc.stop()
  }

}
