package com.atguigu.spark.sql.api

import java.util.Properties

import org.apache.spark.sql.SparkSession
//todo 读取数据
object spk_mysql_read {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("mysql").master("local[*]").getOrCreate()
    import spark.implicits._
    //方法一：
    spark.read.format("jdbc")
      .option("url","jdbc:mysql://hadoop102:3306/mydb")
      .option("driver","com.mysql.jdbc.Driver")
      .option("user","root")
      .option("password","atguigu")
      .option("dbtable", "user")
      .load()
      .show
    //方法二
    val properties = new Properties()
    properties.setProperty("user","root")
    properties.setProperty("password","atguigu")
    spark.read.jdbc("jdbc:mysql://hadoop102:3306/mydb","user",properties).show()
    spark.stop()
  }
}
