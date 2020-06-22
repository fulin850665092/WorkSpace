package com.atguigu.req_core.acc

import com.atguigu.req_core.bean.HotCategory
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

class Top10_acc extends  AccumulatorV2[(String, String),mutable.Map[String,HotCategory]]{

  private var top10Map: mutable.Map[String, HotCategory] = mutable.Map[String, HotCategory]()

  override def isZero: Boolean = top10Map.isEmpty

  override def copy(): AccumulatorV2[(String, String), mutable.Map[String, HotCategory]] = new Top10_acc

  override def reset(): Unit = top10Map.clear()

  override def add(kv:(String, String)): Unit = {

      if(kv._1=="clik"){
        val category = top10Map.getOrElse(kv._2,HotCategory(kv._2,0,0,0))
        category.clickCount+=1
        top10Map.update(kv._2,category)

      }
      else if(kv._1=="order"){
        val category = top10Map.getOrElse(kv._2,HotCategory(kv._2,0,0,0))
        category.orderCount+=1
        top10Map.update(kv._2,category)

      }
      else if(kv._1=="pay") {
        val category = top10Map.getOrElse(kv._2,HotCategory(kv._2,0,0,0))
        category.payCount+=1
        top10Map.update(kv._2,category)

      }
      else top10Map
  }

  override def merge(other: AccumulatorV2[(String, String), mutable.Map[String, HotCategory]]): Unit = {
    this.top10Map=
        this.top10Map.foldLeft(other.value)(
            (map,kv)=>{
              val category = map.getOrElse(kv._1,HotCategory(kv._1, 0, 0, 0))
              category.clickCount +=kv._2.clickCount
              category.orderCount+=kv._2.orderCount
              category.payCount+=kv._2.payCount
              map.update(kv._1,category)
              map
            }
        )
  }

  override def value: mutable.Map[String, HotCategory] = top10Map
}
