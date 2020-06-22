package com.atguigu.req_core.acc

import com.atguigu.req_core.bean.HotCategory
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

class Top10_acc3 extends  AccumulatorV2[(String, String),mutable.Map[String,HotCategory]]{

  private var top10Map: mutable.Map[String, HotCategory] = mutable.Map[String, HotCategory]()

  override def isZero: Boolean = top10Map.isEmpty

  override def copy(): AccumulatorV2[(String, String), mutable.Map[String, HotCategory]] = new Top10_acc3

  override def reset(): Unit = top10Map.clear()

  override def add(kv:(String, String)): Unit = {
     val category = top10Map.getOrElse(kv._2,HotCategory(kv._2,0,0,0))
     kv._1  match {
                    case "clik" =>  category.clickCount+=1
                    case "order" => category.orderCount+=1
                    case "pay" =>   category.payCount+=1
                    case _ =>       category
                  }
      top10Map.update(kv._2,category)
//      if(kv._1=="clik"){
//        val category = top10Map.getOrElse(kv._2,HotCategory(kv._2,0,0,0))
//        category.clickCount+=1
//        top10Map.update(kv._2,category)
//        top10Map
//      }
//      else if(kv._1=="order"){
//        val category = top10Map.getOrElse(kv._2,HotCategory(kv._2,0,0,0))
//        category.orderCount+=1
//        top10Map.update(kv._2,category)
//        top10Map
//      }
//      else if(kv._1=="pay") {
//        val category = top10Map.getOrElse(kv._2,HotCategory(kv._2,0,0,0))
//        category.payCount+=1
//        top10Map.update(kv._2,category)
//        top10Map
//      }
//      else top10Map
  }

  override def merge(other: AccumulatorV2[(String, String), mutable.Map[String, HotCategory]]): Unit = {

      other.value.foreach {
        case (k, cate)=>{
            val hotCategory = top10Map.getOrElse(k,HotCategory(k,0,0,0))
            hotCategory.clickCount +=cate.clickCount
            hotCategory.orderCount+=cate.orderCount
            hotCategory.payCount+=cate.payCount
            top10Map.update(k,hotCategory)
        }
      }

//    this.top10Map=
//        this.top10Map.foldLeft(other.value)(
//            (map,kv)=>{
//              val category = map.getOrElse(kv._1,HotCategory(kv._1, 0, 0, 0))
//              category.clickCount +=kv._2.clickCount
//              category.orderCount+=kv._2.orderCount
//              category.payCount+=kv._2.payCount
//              map.update(kv._1,category)
//              map
//            }
//        )
  }

  override def value: mutable.Map[String, HotCategory] = top10Map
}
