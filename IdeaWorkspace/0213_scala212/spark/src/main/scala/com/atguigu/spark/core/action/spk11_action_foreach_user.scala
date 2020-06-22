package com.atguigu.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object spk11_action_foreach_user{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD -- 行动算子：序列化

    val rdd = sc.makeRDD(List(new User(),new Emp()),2)


    rdd.foreach(s=>{
      println(s)
    })
    sc.stop()
  }
  case  class  User()
  case  class  Emp()
}
