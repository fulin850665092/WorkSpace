package com.atguigu.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object spk7_action_aggregate{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD -- 行动算子： 	aggregate
        //分区的数据通过初始值和分区内的数据进行聚合，然后再和初始值进行分区间的数据聚合
      //分区内计算会使用，分区间计算也会使用
//      （10 ，4，1），（10，3，3），10 =》 30
    val rdd = sc.makeRDD(List(4,1,3,2),2)
    println(rdd.aggregate(10)(
      (x, y) => math.max(x, y),
      (x, y) => x + y
    ))
    println(rdd.aggregate(10)(_+_,_+_ ))
    //todo fold:aggregate分区内和分区间计算规则一样时
    println(rdd.fold(10)(_ + _))

    sc.stop()
  }

}
