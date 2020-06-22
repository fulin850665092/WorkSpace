package com.atguigu.spark.sql.UDAF

import org.apache.spark.sql.expressions.{Aggregator, MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._
import org.apache.spark.sql.{Encoder, Encoders, Row, SparkSession}

object avg3 {
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
    val ds = rdd.map(s=>user(s._1,s._2)).toDS()

    //创建udaf函数
    val userUDAF = new userUDAF1
    //将udaf函数注册成column
    val column = userUDAF.toColumn
    //使用udaf函数
//    spark.sql("select column from user").show()
    ds.select(column).show()


  }

  case class user(name: String, age: Long)

  case class bufferC(var sum: Long,var  count: Long)

  class userUDAF1 extends Aggregator[user, bufferC, Double]{
    override def zero: bufferC =bufferC(0L,0L)

    override def reduce(b: bufferC, a: user): bufferC = {
      b.sum=b.sum+ a.age
      b.count=b.count+1
      b
    }

    override def merge(b1: bufferC, b2: bufferC): bufferC = {
      b1.count=b1.count+b2.count
      b1.sum=b1.sum+b2.sum
      b1
    }


    override def finish(reduction: bufferC): Double =reduction.sum.toDouble/reduction.count

    override def bufferEncoder: Encoder[bufferC] = Encoders.product

    override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
  }
}
