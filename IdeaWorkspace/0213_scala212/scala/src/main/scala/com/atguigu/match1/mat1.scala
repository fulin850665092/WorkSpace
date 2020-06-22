package com.atguigu.match1

object mat1 {
  def main(args: Array[String]): Unit = {
    var age = 59
    age match {
      case 20 => println(20)
      case 30 => println(30)
      case _ => println("no 30 no else 20")
    }
  }
}
