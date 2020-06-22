package com.atguigu.execption

object exe1 {
  def main(args: Array[String]): Unit = {
    try {
      var n= 10 / 0
    }catch {
      case ex: ArithmeticException=>{
        // 发生算术异常
        println("发生算术异常")
      }
      case ex: Exception=>{
        // 对异常处理
        println("发生了异常1")
      }
    }finally {
      println("finally")
    }
  }
}
