package com.atguigu.implicitTes

 object imp1 {
  def main(args: Array[String]): Unit = {

   implicit def getInt(i:Double):Int={
      i.toInt
    }

    val i:Int = 2.0
    println(i)
  }
}
