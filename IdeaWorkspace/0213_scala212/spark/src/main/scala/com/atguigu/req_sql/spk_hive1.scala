package com.atguigu.req_sql

import org.apache.spark.sql.SparkSession

object spk_hive1 {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "atguigu")

    val spark = SparkSession
                                  .builder()
                                  .enableHiveSupport()
                                  .master("local[*]")
                                  .appName("hive")
                                  .config("spark.sql.warehouse.dir", "hdfs://hadoop102:8020/user/hive/warehouse")
                                  .getOrCreate()

    spark.sql("use atguigu")

    spark.sql(
      """
        |select ()
      """.stripMargin)


    spark.stop()
  }
}
