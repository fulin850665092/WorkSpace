package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk2_RDD {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象）
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo 从内存中创建RDD
      //makeRDD
          //第一个参数（seq）：数据源
          //第二个参数（numSlices:Int）：默认并行度
            //todo RDD中的分区数量就是并行度。设定并行度，就是在设置分区数量
               // todo 默认值：//scheduler.conf.getInt("spark.default.parallelism", totalCores)
    val list = List(1,2,3,4,5)

    val rdd2 = sc.makeRDD(list)
    println(rdd2.collect().mkString(","))
//    RDD2.saveAsTextFile("0213_scala212/output/spk2")
    println(rdd2.partitions.length)

    //todo 分区底层
    /*
      def positions(length: Long, numSlices: Int): Iterator[(Int, Int)] = {
            (0 until numSlices).iterator.map { i =>
              val start = ((i * length) / numSlices).toInt
              val end = (((i + 1) * length) / numSlices).toInt
              (start, end)
            }
          }
          (1,2,3,4,5)
        0->(索引)[0，1）-> 1
        1->(索引)[1，3）->2,3
        2->(索引)[3，5）->4,5
        //根据返回的元组，做数据切分
      positions(array.length, numSlices).map { case (start, end) =>
                array.slice(start, end).toSeq
            }.toSeq
     */
    //array.slice(start, end):截取数组数据
    val list1 = List(1,2,3,4,5)
    val rdd3 = sc.makeRDD(list1,3)
    //    RDD2.saveAsTextFile("0213_scala212/output/spk2")
    println(rdd3.partitions.length)
    Array(1, 2, 3, 4, 5).slice(0, 1).iterator.foreach(print)
    Array(1, 2, 3, 4, 5).slice(1, 3).iterator.foreach(print)
    Array(1, 2, 3, 4, 5).slice(3, 5).iterator.foreach(print)



    sc.stop()
  }
}
