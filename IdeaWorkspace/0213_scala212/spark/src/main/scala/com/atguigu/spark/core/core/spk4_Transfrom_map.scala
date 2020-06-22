package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk4_Transfrom_map {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
      //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
    val list = List(1,2,3,4)
    //map 方法在调用时会触发执行
//    val list1 = list.map(s=>{
//      println("map.....")
//      s*2+"s"
//    })
//    println(list1)

    //todo RDD转换算子 - map
    val rdd = sc.makeRDD(list)
    //map,可以是数值变化，也可以是类型变化
      //map算子执行不会马上执行，而是延迟加载，而是在collect之后执行
      //map算子中的逻辑是在Executor端执行的
        //map外部的代码执行在Driver  内部的代码执行在Executor端，为了和scala的map方法区分，所以rdd的map叫算子
    val rdd1 = rdd.map(s => {
      println("map2.....")
      s * 2 + "s"
    })
    println("xxxxxx")
    println(rdd1.collect().mkString(","))
//    println(rdd.map(_ * 2).collect().mkString(","))
//    println(rdd.map(_ * 2).collect().mkString(","))




    sc.stop()
  }
}
