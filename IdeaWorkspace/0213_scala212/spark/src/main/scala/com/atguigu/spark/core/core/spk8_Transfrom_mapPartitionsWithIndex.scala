package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk8_Transfrom_mapPartitionsWithIndex {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
      //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
    val list = List(1,2,3,4)

    //todo RDD转换算子 - mapPartitionsWithIndex
        //将待处理的数据以分区为单位发送到计算节点进行处理，这里的处理是指可以进行任意的处理
        // ，哪怕是过滤数据，在处理时同时可以获取当前分区索引。
    val rdd = sc.makeRDD(list,2)

    val rdd1 = rdd.mapPartitionsWithIndex((index,datas)=> {
      datas.map((index,_))
    })
    println(rdd1.collect().mkString(","))

    println(rdd.mapPartitionsWithIndex((index, dt) => {
      List((index, dt.max)).toIterator
    }).collect().mkString(","))


    sc.stop()
  }
}
