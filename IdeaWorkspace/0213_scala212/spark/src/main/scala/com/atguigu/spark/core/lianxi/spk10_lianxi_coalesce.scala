package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}


object spk10_lianxi_coalesce{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
   val rdd = sc.makeRDD(List(
     1,2,3,4,1,2,4,5,6
   ),3)

    //todo 思考一个问题：我想要扩大分区，怎么办？
    //，一般coalesce用来缩减分区，扩大分区用，repartition
    rdd
      .coalesce(4,true)
      .mapPartitionsWithIndex((index,dt)=>{
        dt.map((index,_))
      })
      .collect()
      .foreach(println)

    println(rdd
      .repartition(4)
      .mapPartitionsWithIndex((index, dt) => {
        dt.map((index, _))
      })
      .collect()
      .mkString(",")
    )

    sc.stop()
  }
}
