package com.atguigu.spark.core.dep

import org.apache.spark.{SparkConf, SparkContext}

/**
  * todo 这里所谓的依赖关系，其实就是RDD之间的关系
  *     窄依赖：窄依赖表示每一个父RDD的Partition最多被子RDD的一个Partition使用，窄依赖我们形象的比喻为独生子女。
  *     宽依赖：宽依赖表示同一个父RDD的Partition被多个子RDD的Partition依赖，会引起Shuffle，总结：宽依赖我们形象的比喻为超生。
  */
object spk_dep1 {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("tes").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd = sc.makeRDD(List(
      "hello scala","hello spark"
    ))

    println(rdd.dependencies)
    println("--------------------------")

    val wordRdd = rdd.flatMap(_.split(" "))
    //todo OneToOneDependency  ,一对一，窄依赖（extends NarrowDependency）
    println(wordRdd.dependencies)
    println("--------------------------")

    val mapRdd = wordRdd.map((_,1))
    //todo OneToOneDependency   ,一对一，窄依赖（extends NarrowDependency）
    println(mapRdd.dependencies)
    println("--------------------------")


    val redecrRdd = mapRdd.reduceByKey(_+_)
    //todo ShuffleDependency ，打乱重组了，宽依赖（不是窄就宽）
    println(redecrRdd.dependencies)
    println("--------------------------")

    redecrRdd.collect().foreach(println)
    //todo 关闭
    sc.stop()
  }
}
