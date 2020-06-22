package com.atguigu.spark.core.exem

import org.apache.spark.{SparkConf, SparkContext}

/**
  *TODO  实现方案二: 一次性统计每个品类点击的次数，下单的次数和支付的次数：
  * （品类，（点击总数，下单总数，支付总数））
  */
object tes1_2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("tes").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("0213_scala212/input/user_visit_action.txt")
    rdd.flatMap(
      s=>{
          val dt = s.split("_")
          if(dt(6) != "-1")   List((dt(6),(1,0,0)))                                //19
          else if(dt(8) != "null") dt(8).split(",").map((_,(0,1,0)))       //2,6,3,14
          else if(dt(10) != "null") dt(10).split(",").map((_,(0,0,1)))      //15,1,20
          else Nil
      })
    .reduceByKey(
      (x, y) => {
        (x._1 + y._1, x._2 + y._2, x._3 + y._3) })
    .sortBy(_._2, false)
//    .collect()   //行动算子
    .take(10)      //行动算子
    .foreach(println)

    //todo 关闭
    sc.stop()
  }
}
