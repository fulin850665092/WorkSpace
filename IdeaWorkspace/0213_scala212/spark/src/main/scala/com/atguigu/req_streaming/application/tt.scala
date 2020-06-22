package com.atguigu.req_streaming.application

import com.atguigu.req_streaming.util.JdbcUtil
import com.atguigu.summer.framework.util.AppUtil

object tt {
  def main(args: Array[String]): Unit = {
    val rdd = AppUtil.SparkContext()
    val rdd1 = rdd.textFile("0213_scala212/input/banner1.txt")
    rdd1.foreach(println)
  }
}
