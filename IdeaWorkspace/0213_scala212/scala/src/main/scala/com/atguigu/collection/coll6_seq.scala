package com.atguigu.collection

import scala.collection.mutable.ListBuffer

object coll6_seq {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4)
    val list1 = List(1,2,3,4)
    val list2 = List.concat(list,list1)
    println(list2.mkString(","))
    val strList = List.fill[String](3)("a")
    val array = list.toArray
    val buffer = list.toBuffer
    val list4 = ListBuffer(1,2,3,4).toList
    println(strList.mkString(","))
  }
}

