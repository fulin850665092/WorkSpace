package com.atguigu.spark.core.dep

import org.apache.spark.{SparkConf, SparkContext}

/**
  * todo 血缘关系：RDD的Lineage会记录RDD的元数据信息和转换行为，
  *  它可以根据这些信息来重新运算和恢复丢失的数据分区。
  */
object spk_dep {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("tes").setMaster("local[*]")
    val sc = new SparkContext(conf)
    //todo 产生Rdd  -》new ParallelCollectionRDD
    val rdd = sc.makeRDD(List(
      "hello scala","hello spark"
    ))
    println(rdd.toDebugString)
    println("--------------------------")

    //todo new MapPartitionsRDD ->  new ParallelCollectionRDD
    val wordRdd = rdd.flatMap(_.split(" "))
    println(wordRdd.toDebugString)
    println("--------------------------")

    //todo  new MapPartitionsRDD -> new MapPartitionsRDD ->  new ParallelCollectionRDD
    val mapRdd = wordRdd.map((_,1))
    println(mapRdd.toDebugString)
    println("--------------------------")

    //todo new ShuffledRDD -> new MapPartitionsRDD -> new MapPartitionsRDD ->  new ParallelCollectionRDD
    val redecrRdd = mapRdd.reduceByKey(_+_)
    println(redecrRdd.toDebugString)
    println("--------------------------")

    redecrRdd.collect().foreach(println)
    //todo 关闭
    sc.stop()
  }
}
