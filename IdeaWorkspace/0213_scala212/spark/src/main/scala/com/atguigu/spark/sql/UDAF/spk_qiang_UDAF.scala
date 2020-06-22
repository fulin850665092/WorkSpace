package com.atguigu.spark.sql.UDAF

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object spk_qiang_UDAF {
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


    //封装为DataSet
    val ds = df.as[User01]

    //创建聚合函数
    var myAgeUdaf1 = new MyAveragUDAF1
    //将聚合函数转换为查询的列
    val col= myAgeUdaf1.toColumn

    //查询
    ds.select(col).show()
  }
}
