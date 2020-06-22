package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 将List(List(1,2),3,List(4,5))进行扁平化操作
  */
object spk4_lianxi_flatMap {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)

    println(
      sc
        .makeRDD(List(List(1,2),3,List(4,5)), 2)
        .flatMap(dt=>{
          dt match {
            case i:Int=>List(i)
            case j:List[_] =>j
          }
        }
        )
        .collect()
        .mkString(",")
    )
    sc.stop()
  }
}
