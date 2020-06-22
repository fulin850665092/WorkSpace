package com.atguigu.collection

import scala.collection.mutable

object coll8_set {
  def main(args: Array[String]): Unit = {
    //可变set:无序不可重复
    val set = mutable.Set(1,2,3,4,3,5,6)
    set.add(9)
    set.update(10,true)
    set.remove(5)
    println(set)
    val set2 = mutable.Set(1,2,3,4)
    val set3 = mutable.Set(3,4,5,6)
    //取集合set2和set3的交集
    val set4 = set2 & set3
    //取集合set2和set3的差集
    val set5 = set2 &~ set3
    println(set4)
    println(set5)
    val numbers = Seq(1,2,3,4,5,6,7,8,9,10)
    println(numbers.filter(n => n % 2 == 0))
  }
}
