package com.atguigu.req_sql

import org.apache.spark.sql.SparkSession

object spk1_jianbiao {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "atguigu")
    val spark = SparkSession
                                  .builder()
                                  .enableHiveSupport()
                                  .master("local[*]")
                                  .appName("hive")
                                  .config("spark.sql.warehouse.dir", "hdfs://hadoop102:8020/user/hive/warehouse")
                                  .getOrCreate()

    spark.sql("create database atguigu ")
    spark.sql("use atguigu")

    //todo user_visit_action
    spark.sql(
      """
        |CREATE TABLE `user_visit_action`(
        |  `date` string,
        |  `user_id` bigint,
        |  `session_id` string,
        |  `page_id` bigint,
        |  `action_time` string,
        |  `search_keyword` string,
        |  `click_category_id` bigint,
        |  `click_product_id` bigint,
        |  `order_category_ids` string,
        |  `order_product_ids` string,
        |  `pay_category_ids` string,
        |  `pay_product_ids` string,
        |  `city_id` bigint)
        |row format delimited fields terminated by '\t'
      """.stripMargin)
    spark.sql(
      """
        |load data local inpath '0213_scala212/input/user_visit_action1.txt' into table user_visit_action
      """.stripMargin)

    //todo product_info
    spark.sql(
      """
        |CREATE TABLE `product_info`(
        |  `product_id` bigint,
        |  `product_name` string,
        |  `extend_info` string)
        |row format delimited fields terminated by '\t'
      """.stripMargin)

    spark.sql(
      """
        |load data local inpath '0213_scala212/input/product_info.txt' into table product_info
      """.stripMargin)

    //todo city_info
    spark.sql(
      """
        |CREATE TABLE `city_info`(
        |  `city_id` bigint,
        |  `city_name` string,
        |  `area` string)
        |row format delimited fields terminated by '\t'
      """.stripMargin)

    spark.sql(
      """
        |load data local inpath '0213_scala212/input/city_info.txt' into table city_info
      """.stripMargin)



    spark.stop()

  }
}
