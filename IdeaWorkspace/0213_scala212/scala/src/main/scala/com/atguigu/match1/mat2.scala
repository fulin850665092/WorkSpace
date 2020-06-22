package com.atguigu.match1

object mat2 {
  def main(args: Array[String]): Unit = {
    var age = 20
    //有返回值，返回满足条件分组最后一行代码
    val result = age match {
      case 20 => {
        println(20)
        20
      }
      case 30 => {
        println(20)
        30
      }
      case _ => println("no 30 no else 20")
    }
    println(result)
  }
}
