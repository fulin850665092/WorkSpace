package com.atguigu.spark.sql.api

import org.apache.spark.sql.SparkSession

object spk_read1 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("read").master("local[*]").getOrCreate()
    import  spark.implicits._
    spark.read.format("json").load("0213_scala212/input/user.json").show()
    spark.read.json("0213_scala212/input/user.json").show()
    spark.stop()
  }
}
