package com.atguigu.spark.core.cache

import org.apache.spark.{SparkConf, SparkContext}

object spk3_checkpoint {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,4))
    val rdd1 = rdd.map(s => {
//      println("num")
      s
    })
    //持久化可能会造成数据丢失，如果丢失，那么需要重新计算，性能不高
      //所以能够保证数据不丢失，那么是一个好的选择
      //可以将数据保持到检查点，这样是分布式存储，所以比较安全
      // Checkpoint directory has not been set in the SparkContext
      //保存在检查点 前，需要设置检查点路径，todo  路径一般都是分布式存储路径（hdfs）
    sc.setCheckpointDir("0213_scala212/output/cp")
    //检查点，为了数据的准确，他需要在重头执行一次，就等同于开启一个新的作业

    //为了提高效率。一般情况下，是先使用cache后在使用检查点
    val rdd2 = rdd1.cache()
    //todo 检查点会切断RDD的血缘关系。将我们当前的检查点当成数据计算的起点。
      //todo 持久化操作是不能切断血缘关系，因为一旦内存中数据丢失，无法恢复数据
    rdd2.checkpoint()
    println(rdd2.toDebugString)
    println(rdd1.collect().mkString(","))
    println(rdd2.toDebugString)
    println("================")
    println(rdd1.collect().mkString(","))

    sc.stop()
  }
}
