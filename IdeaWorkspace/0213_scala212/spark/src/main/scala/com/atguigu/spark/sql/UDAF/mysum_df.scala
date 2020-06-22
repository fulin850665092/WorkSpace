package com.atguigu.spark.sql.UDAF

import org.apache.spark.sql.SparkSession

object mysum_df {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("sum_UDAF").getOrCreate()
    import  spark.implicits._
    val rdd = spark.sparkContext.makeRDD(
      List(
        ("zhangsan", 23),
        ("lisi", 33),
        ("wangwu", 44),
      )
    )
    val df = rdd.toDF("username","age")

    //创建UFAF函数
    val sumUdaf = new MySumUDTF

    //注册UDAF
    spark.udf.register("MySumUDTF",sumUdaf)

    //创建临时表
    df.createTempView("user")

    //使用UDAF
    spark.sql("select MySumUDTF(age) from user ").show
  }
}
