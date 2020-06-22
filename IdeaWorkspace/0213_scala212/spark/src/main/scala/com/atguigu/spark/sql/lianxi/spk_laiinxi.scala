package com.atguigu.spark.sql.lianxi

import org.apache.spark.{SparkConf, SparkContext}

object spk_laiinxi {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("app").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    val res: (Int, Int) = sc.makeRDD(List(("zhangsan", 20), ("lisi", 30), ("wangw", 40))).map {
      case (name, age) => {
        (age, 1)
      }
    }.reduce(
      (x,y)=>{
        (x._1+y._1,x._2+y._2)
      }
    )

    println(res._1/res._2)
    // 关闭连接
    sc.stop()

  }
}
