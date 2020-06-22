package com.atguigu.scala.fordemo

import scala.collection.immutable

object ForClass {

  def main(args: Array[String]): Unit = {

    val str = "abcd";
    for (s <- str) {
      //s 数据类型是char
      println(s.getClass.getSimpleName)
    }
    val arr = Array(10, 20, 30);
    val arr1 = Array(10, 20, 30, "a")
    //java 用[]表示下表，scala用()表示下标
    arr(1)
    //拿不到下标
    for (elem <- arr) {
      println(elem)
    }
    val index = Array(0, 1, 2)
    for (elem <- index) {
      println(arr(elem))
    }
    //这样就能获取下标  并遍历
    for (elem <- 0 to arr.length-1) {
         println(arr(elem))
    }


  }
}
