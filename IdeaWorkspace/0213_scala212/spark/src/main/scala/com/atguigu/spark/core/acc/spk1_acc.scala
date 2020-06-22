package com.atguigu.spark.core.acc

import org.apache.spark.{SparkConf, SparkContext}

object spk1_acc {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("acc")
    val sc : SparkContext = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,4),1)

//    val i = rdd.reduce(_+_)
//    val d = rdd.sum()
//    println(i)
//    println(d)

    var sun=0
    rdd.foreach(
      s=>{
        sun+=s
      }
    )
    println("sum="+sun)


    sc.stop()
  }
}
