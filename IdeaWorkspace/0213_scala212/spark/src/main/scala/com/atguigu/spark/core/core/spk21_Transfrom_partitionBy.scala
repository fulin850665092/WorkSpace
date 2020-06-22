package com.atguigu.spark.core.core

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object spk21_Transfrom_partitionBy {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 -- 	Key - Value类型类型 partitionBy
        // 只能在kv类型中使用
            //  将数据按照指定Partitioner重新进行分区。Spark默认的分区器是HashPartitioner
              //todo HashPartitioner:分区规则
                    //val rawMod = key.hashcode % numpartitions

        //todo RDD支持分区器对k-v数据进行重分区
          // 默认采用的是HashPartitioner
    val rdd1 = sc.makeRDD(List(1,2,3,4),2)
            //隐式转换
    val rdd2 = rdd1.map((_,1))
    val rdd3 = rdd2.partitionBy(new HashPartitioner(3))
      //todo 多次重分区使用的分区器相同（类型和数量），没有意义，实际不会重新分区
    val rdd4 = rdd3.partitionBy(new HashPartitioner(3))
    println(
      rdd2
        .collect()
        .mkString(",")
    )

    rdd3
      .mapPartitionsWithIndex((index,dt)=>{
        dt.map((index,_))
      })
      .collect()
      .foreach(println)
    println("重分区=========")
    rdd4
      .mapPartitionsWithIndex((index,dt)=>{
        dt.map((index,_))
      })
      .collect()
      .foreach(println)

    sc.stop()
  }

}
