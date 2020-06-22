package com.atguigu.spark.core.broadcast

import org.apache.spark.{SparkConf, SparkContext}

object spk1_broadcast {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("acc")
    val sc : SparkContext = new SparkContext(sparkConf)
    //笛卡尔积，如果有shuffle，性能会非常差
    val rdd1 = sc.makeRDD(List(("a",1),("b",1),("c",1)))
//    val rdd2 = sc.makeRDD(List(("a",1),("b",1),("c",1)))
//    val joinrdd = rdd1.join(rdd2)
    val list = List(("a",1),("b",1),("c",1))
    println(
      rdd1.map {
          case (word, cout1) => {
            var cout2 = 0
            for ((k, v) <- list) {
              if (k == word) {
                cout2 = v
              }
            }
            (word, (cout1, cout2))
          }
      }
      .collect()
      .mkString(",")
    )


    //todo 广播变量
//    val rddB = sc.broadcast(joinrdd)

  }
}
