package com.atguigu.scala

object DataType {
  def main(args: Array[String]): Unit = {
    val  a = foo();
    println(a)//unit=void (java)
    val  b = (); //unit 只有一个值：（）
    val s1:String =null
    val  s2:Object = null //Null 只有一个值：null，可以转换为所有的引用类型
  }
  def foo(): Unit ={

  }
}
