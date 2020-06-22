package com.atguigu.spark.sql.api

import java.util.Properties

import com.atguigu.spark.sql.api.spk1_core.user
import org.apache.spark.sql.{SaveMode, SparkSession}

object spk_mysql_write {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local[*]").appName("mysql").getOrCreate()
    import  spark.implicits._

    val df = spark.sparkContext.makeRDD(List(user(4,"lisi"), user(5,"zs"))).toDF()
    df.write.format("jdbc")
      .option("url","jdbc:mysql://hadoop102:3306/mydb")
      .option("driver","com.mysql.jdbc.Driver")
      .option("user","root")
      .option("password","atguigu")
      .option("dbtable", "user")
      .mode("append")
      .save

    val props = new Properties()
    props.setProperty("user","root")
    props.setProperty("password","atguigu")
    df.write.mode(SaveMode.Append).jdbc("jdbc:mysql://hadoop102:3306/mydb","user",props)
  }
  case class user(id:Int,name:String)
}
