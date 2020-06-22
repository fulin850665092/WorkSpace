package com.atguigu.spark.core.broadcast

import org.apache.spark.{SparkConf, SparkContext}

object spk2_broadcast2 {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("acc")
    val sc : SparkContext = new SparkContext(sparkConf)


    val rdd1 = sc.makeRDD(List(("a",1),("b",1),("c",1)))
    val list = List(("a",1),("b",1),("c",1))
    //todo 声明广播变量：只能读，不能修改
    val bcList = sc.broadcast(list)
    println(
      rdd1.map {
          case (word, cout1) => {
            var cout2 = 0
            //todo 使用广播变量
             //还有机架感知，就近拉取值，Executor都没有，就从 Driver取
            for ((k, v) <- bcList.value) {
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
