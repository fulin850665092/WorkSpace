package com.atguigu.spark.sql.UDAF

import org.apache.spark.sql.SparkSession

object mysum_ds {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("sum_UDAF").getOrCreate()
    import spark.implicits._
    val rdd = spark.sparkContext.makeRDD(
      List(
        ("zhangsan", 23),
        ("lisi", 33),
        ("wangwu", 44),
      )
    )
    val df = rdd.toDF("username","age")
    val ds = df.as[user]
    //创建UFAF函数
    val sumUdaf = new MySumUDTF2
    //将聚合函数转换为查询的列
    val column = sumUdaf.toColumn
    ds.select(column).show
  }
}
