package com.atguigu.req_core.acc

import com.atguigu.spark.core.project.bean.HotCategory
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

class Top10_acc2 extends AccumulatorV2[String,mutable.Map[String,HotCategory]]{

  private var hostMap: mutable.Map[String, HotCategory] = mutable.Map[String, HotCategory]()

  override def isZero: Boolean = hostMap.isEmpty

  override def copy(): AccumulatorV2[String, mutable.Map[String, HotCategory]] = new Top10_acc2

  override def reset(): Unit = hostMap.clear()

  override def add(line: String)={

    val dts = line.split("_")

    if (dts(6) != "-1"){
      var id=dts(6)
      val category = hostMap.getOrElse(id,HotCategory(id,0,0,0))
      category.clickCount+=1
      hostMap.update(id,category)
      hostMap
    }
    else if (dts(8)!= "null"){
      val ids = dts(8).split(",")
      ids.foreach(
        id=>{
          val category = hostMap.getOrElse(id,HotCategory(id,0,0,0))
          category.orderCount+=1
          hostMap.update(id,category)
          hostMap
        }
      )
  }
  else if (dts(10)!= "null"){
    val ids = dts(10).split(",")
    ids.foreach(
      id=>{
        val category = hostMap.getOrElse(id,HotCategory(id,0,0,0))
        category.payCount+=1
        hostMap.update(id,category)
        hostMap
      }
    )
  }
    else hostMap
  }

  override def merge(other: AccumulatorV2[String, mutable.Map[String, HotCategory]]): Unit = {
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
