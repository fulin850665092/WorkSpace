package com.atguigu.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object spk9_action_save{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD -- 行动算子： 	saveAsTextFile ,saveAsObjectFile,saveAsSequenceFile

    val rdd = sc.makeRDD(List("a","b","a","b"),1)
    rdd.saveAsTextFile("0213_scala212/output/save/TextFile")
    rdd.saveAsObjectFile("0213_scala212/output/save/ObjectFile")
    rdd.map((_,1)).saveAsSequenceFile("0213_scala212/output/save/SequenceFile")

    sc.stop()
  }

}
