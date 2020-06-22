package com.atguigu.spark.core.lianxi

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object spk23_Test{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo 统计出每一个省份每个广告被点击数量排行的Top3
    val rdd = sc.textFile("0213_scala212/input/agent.log")

    rdd
      .map(s=>((s.split(" ")(1),s.split(" ")(4)),1))
      .reduceByKey(_+_ )
      .map(s=>(s._1._1,(s._1._2,s._2)))
      .groupByKey()
//      .mapValues(_.toList.sortWith((l,r)=>{  //第一种
//        l._2>r._2
//      }).take(3))
      .mapValues(_.toList.sortBy(_._2)(Ordering.Int.reverse).take(3))  //第二种
      .collect()
      .foreach(println)

    sc.stop()
  }

}
