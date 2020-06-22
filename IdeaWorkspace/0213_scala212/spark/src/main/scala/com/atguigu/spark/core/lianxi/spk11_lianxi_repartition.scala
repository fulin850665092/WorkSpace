package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}


object spk11_lianxi_repartition{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
   //todo 思考一个问题：有啥用，抽奖吗？
   val rdd = sc.makeRDD(List(
     1,2,3,4,1,2,4,5,6
   ),3)

    //todo  思考一个问题：coalesce和repartition区别？
    // repartition：底层使用的就是coalesce+shuffle
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
