package com.atguigu.spark.sql.UDAF

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object spk_ruo_UDAF {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("sql")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    //一定要记得导包啊
    import spark.implicits._
    val rdd = spark.sparkContext.makeRDD(
      List(
        ("zhangsan", 23),
        ("lisi", 33),
        ("wangwu", 44),
      )
    )
    val df = rdd.toDF("username","age")
    df.createTempView("user")

    //创建聚合函数
    var myAverage = new MyAveragUDAF

    //在spark中注册聚合函数
    spark.udf.register("avgAge",myAverage)

    spark.sql("select avgAge(age) from user").show()

    spark.stop()
  }
}
