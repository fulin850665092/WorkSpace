package com.atguigu.zhengze

object zz1 {
  def main(args: Array[String]): Unit = {
    val str = "(H|h)ello".r
    val str1 = "HelloWorld"
    println(str.findFirstIn(str1).getOrElse())
  }
}
