package com.atguigu.spark.core.exem

import org.apache.spark.{SparkConf, SparkContext}

/**
  *TODO 实现方案一: 分别统计每个品类点击的次数，下单的次数和支付的次数：
  * （品类，点击总数）（品类，下单总数）（品类，支付总数）
  */
object tes1_1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("tes").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("0213_scala212/input/user_visit_action.txt")
    //todo 缓存rdd，重复使用
    val rddCache = rdd.cache()

    //todo 获取点击品类
    val result =
      rddCache
        .map(s=>(s.split("_")(6),1))
        .filter(_._1 != "-1")
        .reduceByKey(_+_)
    //      result.collect().foreach(println)

    // todo 品类下单统计
    val result1 =
      rddCache
        .map(s=>(s.split("_")(8)))
        .filter(_ !=  "null")
        .flatMap(_.split(",").map((_,1)))
        .reduceByKey(_+_)
    //    result1.collect().foreach(println)

    // TODO 品类支付统计
    val result2 = rddCache
      .map(s => (s.split("_")(10)))
      .filter(_ !=  "null")
      .flatMap(_.split(",").map((_,1)))
      .reduceByKey(_+_)
    //    result3.collect().foreach(println)

    //todo 转换
    // (category, count) => (category, clickcount, ordercount, paycount)
    //点击
    val clickRdd = result.map {
      case (category, count) => {
        (category,( count, 0, 0))
      }
    }
    clickRdd
    //下单
    val orderRdd = result1.map {
      case (category, count) => {
        (category,( 0, count, 0))
      }
    }
    //下单
    val payRdd = result2.map {
      case (category, count) => {
        (category,( 0, 0, count))
      }
    }
    //todo 连接
    val unionRdd = clickRdd.union(orderRdd).union(payRdd)

    //todo 聚合

        unionRdd
          .reduceByKey(
            (x, y) => {
              (x._1 + y._1, x._2 + y._2, x._3 + y._3)
            }
          )
          .sortBy(_._2,false)
          .collect()
          .take(10)
        .foreach(println)


    //todo 关闭
    sc.stop()
  }
}
