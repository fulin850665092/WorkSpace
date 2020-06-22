package com.atguigu.req_sql

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._

//(count,Map(chengshi,sum)
class CityName_UDAF2 extends UserDefinedAggregateFunction{
  override def inputSchema: StructType = StructType(Array(StructField("cityName",StringType)))

  override def bufferSchema: StructType = StructType(Array(StructField("count",LongType),StructField("BufferMap",MapType(StringType,LongType))))

  override def dataType: DataType = StringType

  override def deterministic: Boolean = true

  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0)=0L
    buffer(1)=Map[String,Long]()
  }

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    val count = buffer.getLong(0)
    val map = buffer.getMap[String,Long](1)
    buffer(1)=map.updated(input.getString(0),map.getOrElse(input.getString(0),0L)+1L)
    buffer(0)=count+1
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    val map = buffer1.getMap[String,Long](1)
    val map1 = buffer2.getMap[String,Long](1)
    buffer1(1)=map.foldLeft(map1) {
      case (map,(k,v))=>{
        map.updated(k,map.getOrElse(k,0L)+v)
      }
    }
    buffer1(0)=buffer1.getLong(0)+buffer2.getLong(0)
  }

  override def evaluate(buffer: Row): Any = {
    val totalcnt: Long = buffer.getLong(0)
    val citymap = buffer.getMap[String, Long](1)
    val countList = citymap.toList.sortWith(_._2>_._2).take(2)
    var str=new StringBuffer
    var rest=0L;

    countList.foreach(s=>{
      str.append(s._1 + " " + s._2*100/totalcnt + "%,")
      rest=rest+s._2*100/totalcnt
    })
    str.toString + "其他:" + (100-rest)+ "%"


  }
}
