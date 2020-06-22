package com.atguigu.spark.sql.api

import org.apache.spark.sql.SparkSession

object spk3_core {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("core").getOrCreate()
    spark.sql("select * from json.`0213_scala212/input/user.json`").show
  }
}
