package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

object spk19_lianxi_combineByKey{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 --Key - Value类型 reduceByKey	aggregateByKey foldByKey combineByKey
        //都支持预聚合功能，所以shuffer性能比较高
        //都能实现wordcount功能
    val rdd1 = sc.makeRDD(List(
          ("a", 88), ("b", 95), ("a", 91),
          ("b", 93), ("a", 95), ("b", 98))
          ,2)

    //todo reduceByKey()
          /*
                源码：combineByKeyWithClassTag[V](
                (v: V) => v,    //不会对第一个value进行处理
                func,             //分区内的计算规则
                func,             //分区内的计算规则
                partitioner
                )
           */
    rdd1
      .reduceByKey(_+_)
      .collect()
      .foreach(println)
    println("===============")
    //todo   aggregateByKey()
            /*
              源码：combineByKeyWithClassTag[U](
                        (v: V) => cleanedSeqOp(createZero(), v),  //初始值和第一个v和组合,例（k，0）
                        cleanedSeqOp,                     //分区内的计算规则
                        combOp,                     //分区间的计算规则
                        partitioner         //分区器
                  )
              */

    rdd1
      .aggregateByKey(0)(_+_,_+_)
      .collect()
      .foreach(println)
    println("===============")


    //todo  foldByKey()
            /*
              源码：combineByKeyWithClassTag[V](
                        (v: V) => cleanedFunc(createZero(), v),  //初始值和第一个v和组合,例（k，0）
                        cleanedFunc,      //分区内的计算规则
                        cleanedFunc,      //分区间的计算规则
                        partitioner
                )
            */

    rdd1
      .foldByKey(0)(_+_)
      .collect()
      .foreach(println)
    println("===============")

    //todo combineByKey()
            /*
                  源码：combineByKeyWithClassTag(
                           createCombiner,   //对第一个value进行处理，所以不需要初始值
                           mergeValue,       //
                           mergeCombiners
                      )
                      (null)
             */

    rdd1
      .combineByKey(
        (x:Int)=>(x ,0),  //格式的转换 88-》（88，1）
        (x:(Int,Int),y:Int)=>{
          (x._1+y,x._2)         //数据相加，数量加1
        },
        (x:(Int,Int),y:(Int,Int))=>{
          (x._1+y._1,0)      //数据相加，数据相加
        })
      .collect()
      .foreach(println)


    sc.stop()
  }

}
