package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk27_Transfrom_combineByKey{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 --Key - Value类型 	combineByKey
        //最通用的对key-value型rdd进行聚集操作的聚集函数（aggregation function）。
        // 类似于aggregate()，combineByKey()允许用户返回值的类型与输入不一致。
    //一个参数列表 三个参数
      /* 第一个参数：为了方便，将数据的格式进行转换
         第二个参数：分区内的计算规则
         第三个参数：分区间的计算规则
       */
    val rdd1 = sc.makeRDD(List(
          ("a", 88), ("b", 95), ("a", 91),
          ("b", 93), ("a", 95), ("b", 98))
          ,2)

    //todo 求每个key的平均值
    rdd1
      .combineByKey(
        (x:Int)=>(x ,1),  //格式的转换 88-》（88，1）
        (x:(Int,Int),y:Int)=>{
          (x._1+y,x._2+1)         //数据相加，数量加1
        },
          (x:(Int,Int),y:(Int,Int))=>{
           (x._1+y._1,x._2+y._2)      //数据相加，数据相加
        })
      .mapValues(s=>s._1/s._2)  //求平均值
      .collect()
      .foreach(println)



    sc.stop()
  }

}
