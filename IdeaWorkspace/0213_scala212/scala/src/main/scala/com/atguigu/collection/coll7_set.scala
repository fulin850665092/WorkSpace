package com.atguigu.collection

object coll7_set {
  def main(args: Array[String]): Unit = {
    //Set
      //   无序的不可重复的集合,默认是不可变的
    val set = Set(1,2,3,4,5,6,7,8,3,4)
    val set2 = set+90+6
    println(set eq set2)
    println(set)
    println(set2)
  }
}
