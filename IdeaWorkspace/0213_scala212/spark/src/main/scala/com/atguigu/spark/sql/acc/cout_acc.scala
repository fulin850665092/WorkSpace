package com.atguigu.spark.sql.acc

import org.apache.spark.util.AccumulatorV2

class cout_acc extends  AccumulatorV2[Int,Int]{
  var sum =0
  var count=0
  override def isZero: Boolean ={
   return sum==0&&count==0
  }

  override def copy(): AccumulatorV2[Int, Int] ={
    val newMyAc = new cout_acc
    newMyAc.sum = this.sum
    newMyAc.count = this.count
    newMyAc

  }

  override def reset(): Unit = {
     sum =0
     count= 0
  }

  override def add(v: Int): Unit ={
      sum+=v
      count+=1
  }

  override def merge(other: AccumulatorV2[Int, Int]): Unit = {
    other match {
      case acc: cout_acc =>{
        sum+= acc.sum
        count+=acc.count
      }
      case _ =>
    }
  }

  override def value: Int = sum/count
}
