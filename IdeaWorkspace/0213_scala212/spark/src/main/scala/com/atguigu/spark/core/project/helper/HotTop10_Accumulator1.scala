package com.atguigu.spark.core.project.helper

import com.atguigu.spark.core.project.bean.HotCategory
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

class HotTop10_Accumulator1 extends AccumulatorV2[(String,String),mutable.Map[String,HotCategory]]{

  private var hostMap: mutable.Map[String, HotCategory] = mutable.Map[String, HotCategory]()

  override def isZero: Boolean = hostMap.isEmpty

  override def copy(): AccumulatorV2[(String,String), mutable.Map[String, HotCategory]] = new HotTop10_Accumulator1

  override def reset(): Unit = hostMap.clear()

  override def add(kv: (String,String))={

    if (kv._2=="click"){
        val category = hostMap.getOrElse(kv._1,HotCategory(kv._1,0,0,0))
        category.clickCount+=1
        hostMap.update(kv._1,category)
        hostMap
    }
    else if (kv._2=="order"){
        val category = hostMap.getOrElse(kv._1,HotCategory(kv._1,0,0,0))
        category.orderCount+=1
        hostMap.update(kv._1,category)
        hostMap
    }
    else if (kv._2=="pay"){
        val category = hostMap.getOrElse(kv._1,HotCategory(kv._1,0,0,0))
        category.payCount+=1
        hostMap.update(kv._1,category)
        hostMap
    }
    else hostMap

  }

  override def merge(other: AccumulatorV2[(String,String), mutable.Map[String, HotCategory]]): Unit = {
    this.hostMap=this.hostMap.foldLeft(other.value)(
      (map,kv)=>{
        val category = map.getOrElse(kv._1,HotCategory(kv._1,0,0,0))
        category.clickCount+= kv._2.clickCount
        category.orderCount+=kv._2.orderCount
        category.payCount+=kv._2.payCount
        map.update(kv._1,category)
        map
      }
    )
  }


  override def value: mutable.Map[String, HotCategory] = hostMap
}
