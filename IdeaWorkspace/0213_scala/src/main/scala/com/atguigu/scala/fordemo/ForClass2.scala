package com.atguigu.scala.fordemo

import scala.collection.immutable
import scala.util.control.Breaks._

object ForClass2 {

  def main(args: Array[String]): Unit = {

    //这样就能获取下标  并遍历,2表示步长（java for i，路面i+2）
    for (elem <- 0 to(100, 2)) {
      println(elem)
    }
    //end 大于 start
    println(100 to 0)
    //1 减到100，不满足，end 大于 start
    println(0 to(100, -1))
    //递减
    println(100 to(1, -1))
    //递减(反转)，推荐用
    println(1 to 100 reverse)
    val arr = Array(10, 20, 30)
    //to:表示大于等于0，小于等于arr.length
    for (elem <- 0 to arr.length - 1) {
      println(arr(elem))
    }
    //until:大于等于0，小于arr.length（所以，要下标遍历推荐 until）
    for (elem <- 0 until arr.length) {
      println(arr(elem))
    }
    //获取奇数
    for (elem <- 1 to 100) {
      if (elem % 2 == 1) {
        println(elem)
      }
    }
    //获取奇数 (循环守卫)
    for (elem <- 1 to 100 if elem % 2 == 1) {
      println(elem)
    }
    //提前结束循环：
    breakable(
      for (elem <- 0 to 100) {
        println(elem)
        if (elem == 5) break
      })

    //提前结束循环：
    for (elem <- 0 to 10) {
      println(elem)
      if (elem == 5) return
    }

  }
}
