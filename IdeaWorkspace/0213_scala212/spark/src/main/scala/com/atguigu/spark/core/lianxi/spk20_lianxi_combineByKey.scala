package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

object spk20_lianxi_combineByKey{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo 求每个key的平均值
    val rdd = sc.makeRDD(List(
          ("a", 88), ("b", 95), ("a", 91),
          ("b", 93), ("a", 95), ("b", 98))
          ,2)
    rdd
      .combineByKey(
        (x:Int)=> (x , 1),    //根据 k 封住第一个value-》（88，1）
        (x:(Int,Int),y:Int)=>(x._1+y,x._2+1),  //将分区中的相同k，的value进行连接
        (x:(Int,Int),y:(Int,Int))=>(x._1+y._1,x._2+y._2))    //将不同分区的相同k进行求和
      .map(s=>(s._1,s._2._1/s._2._2))    //求平均值
      .collect().foreach(println)


    sc.stop()
  }

}
