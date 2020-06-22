package com.atguigu.req_sql

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._

//(count,Map(chengshi,sum)
class CityName_UDAF extends UserDefinedAggregateFunction{
  override def inputSchema: StructType = {
    StructType(Array(StructField("cityName", StringType)))
  }

  // count, (北京 : 100，天津:50)
  override def bufferSchema: StructType = {
    StructType(Array(StructField("cityToCount", MapType(StringType, LongType)), StructField("count", LongType)))
  }

  override def dataType: DataType = StringType

  override def deterministic: Boolean = true
  //初始化
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = Map[String, Long]()
    buffer(1) = 0L
  }

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    val cityName = input.getString(0)
    val map= buffer.getAs[Map[String, Long]](0)
    buffer(0) =  map.updated(cityName,map.getOrElse(cityName, 0L) + 1L)
    buffer(1) = buffer.getLong(1) + 1L
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {

    val map1 = buffer1.getAs[Map[String, Long]](0)
    val map2 = buffer2.getAs[Map[String, Long]](0)

    buffer1(0) = map1.foldLeft(map2){
      case ( map, (k, v) ) => {
        map.updated(k,map.getOrElse(k, 0L) + v)
      }
    }
    buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)
  }

  override def evaluate(buffer: Row): Any = {

    // 获取城市点击次数，并根据点击次数进行排序取2条
    val map= buffer.getAs[Map[String, Long]](0)
    //按照点击次数排序(倒叙)
    val remarkList = map.toList.sortWith(_._2>_._2)
    //长度大于2
    if ( remarkList.size > 2 ) {
      val restList = remarkList.take(2)
      val cityList= restList.map {
        case (cityName, clickCount) => {
          cityName + clickCount.toDouble / buffer.getLong(1) * 100 + "%"
        }
      }
      cityList.mkString(", ") + ", 其他 " + ( remarkList.tail.tail.map(_._2).sum / buffer.getLong(1) * 100 + "%" )

    } else {
      val cityList= remarkList.map {
        case (cityName, clickCount) => {
          cityName + clickCount.toDouble / buffer.getLong(1) * 100 + "%"
        }
      }
      cityList.mkString(", ")
    }


  }

}
