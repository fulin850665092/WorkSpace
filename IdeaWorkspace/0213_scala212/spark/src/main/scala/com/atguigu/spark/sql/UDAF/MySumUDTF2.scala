package com.atguigu.spark.sql.UDAF

import org.apache.spark.sql.{Encoder, Encoders}
import org.apache.spark.sql.expressions.Aggregator


//输入的类型
case class user(username:String,age:Long)
//缓存的类型
case class AggreBuffer(var sum:Long)

class MySumUDTF2 extends Aggregator[user,AggreBuffer,Long]{
  //初始化AggreBuffer的值
  override def zero: AggreBuffer = AggreBuffer(0L)
  //区内聚合
  override def reduce(b: AggreBuffer, a: user): AggreBuffer = {
    b.sum=b.sum + a.age
    b
  }
  //区间合并
  override def merge(b1: AggreBuffer, b2: AggreBuffer): AggreBuffer = {
    b1.sum=b1.sum+b2.sum
    b1
  }
  //返回的数据
  override def finish(reduction: AggreBuffer): Long = reduction.sum
  //缓存的解码
  //Dataset 默认的编码解码器，用于序列化，固定写法
  //自定义类型就是produce，自带类型根据类型选择
  override def bufferEncoder: Encoder[AggreBuffer] = Encoders.product
  //输出的解码
  override def outputEncoder: Encoder[Long] = Encoders.scalaLong
}
