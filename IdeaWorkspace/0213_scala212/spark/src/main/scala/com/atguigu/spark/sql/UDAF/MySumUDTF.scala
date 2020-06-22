package com.atguigu.spark.sql.UDAF

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, IntegerType, LongType, StructField, StructType}

class MySumUDTF extends UserDefinedAggregateFunction{

  //聚合函数输入参数数据类型
  override def inputSchema: StructType = StructType(Array(StructField("age",IntegerType)))
  //聚合函数缓冲区中值的数据类型
  override def bufferSchema: StructType = StructType(Array(StructField("sum",LongType)))
  //返回值的数据类型
  override def dataType: DataType =LongType
  //w稳定性，对于相同的输入是否一直返回相同的输出
  override def deterministic: Boolean = true
  //函数缓冲区初始化
  override def initialize(buffer: MutableAggregationBuffer): Unit = buffer(0)=0L  //存年龄的总和
  //更新缓冲区中的数据(分区内累加)
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = if (!input.isNullAt(0)) buffer(0)= buffer.getLong(0)+input.getInt(0)
  //合并缓冲区(分区间合并)
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = buffer1(0)=buffer1.getLong(0)+buffer2.getLong(0)
  //计算最终结果
  override def evaluate(buffer: Row): Any =buffer(0)
}
