package com.atguigu.spark.sql.api

import org.apache.spark.sql.SparkSession

object spk_hive_neibu {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
                                  .builder()
                                  .master("local[*]")
                                  .appName("hive")
                                  .getOrCreate()
    spark.sql("create table aa(id int)")
    spark.sql("show tables").show()
    spark.stop()

  }
}
