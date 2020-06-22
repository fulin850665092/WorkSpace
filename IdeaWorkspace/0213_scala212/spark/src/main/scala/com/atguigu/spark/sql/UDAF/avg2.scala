package com.atguigu.spark.sql.UDAF

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructField, StructType}

object avg2 {
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
    val df = rdd.toDF("name","age")
    df.createTempView("user")

    //创建udaf函数
    val userUDAF = new userUDAF
    //注册udaf函数
    spark.udf.register("userUDAF",userUDAF)
    //使用udaf函数
    spark.sql("select userUDAF(age) from user").show()


  }
  class  userUDAF extends UserDefinedAggregateFunction{

    override def inputSchema: StructType = StructType(Array(StructField("age",LongType)))

    override def bufferSchema: StructType = StructType(Array(StructField("count",LongType),StructField("sum",LongType)))

    override def dataType: DataType =DoubleType

    override def deterministic: Boolean = true

    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      buffer(0)=0L
      buffer(1)=0L
    }

    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      buffer(0)=buffer.getLong(0)+input.getLong(0)
      buffer(1)=buffer.getLong(1)+1
    }

    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      buffer1(0)=buffer1.getLong(0)+buffer2.getLong(0)
      buffer1(1)=buffer1.getLong(1)+buffer2.getLong(1)
    }

    override def evaluate(buffer: Row): Any = buffer.getLong(0).toDouble/buffer.getLong(1)
  }
}
