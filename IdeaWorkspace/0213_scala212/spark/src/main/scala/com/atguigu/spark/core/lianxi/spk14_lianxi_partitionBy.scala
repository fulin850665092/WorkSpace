package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}


object spk14_lianxi_partitionBy{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo 如果重分区的分区器和当前RDD的分区器一样怎么办？
          //分区无效，不会重复分区
    // todo  思考一个问题：Spark还有其他分区器吗？
          //HashPartitioner，rangePartitioner，pythonPartitioner
    // todo 思考一个问题：如果想按照自己的方法进行数据分区怎么办？
            //自定义分区：重新一个类 extends Partitioner，重写方法（仿者写，无敌）
    // todo 思考一个问题：哪那么多问题？

    sc.stop()
  }
}
