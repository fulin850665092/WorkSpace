package com.atguigu.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object spk2_action_collect{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD -- 行动算子： 	reduce
        //行动算子：指让当前的应用程序执行
        //在驱动程序中，以数组Array的形式返回数据集的所有元素
    val rdd = sc.makeRDD(List(1,2,3,4))

    rdd.collect().foreach(println)

    sc.stop()
  }

}
