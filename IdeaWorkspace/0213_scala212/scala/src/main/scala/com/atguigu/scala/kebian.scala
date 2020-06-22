package com.atguigu.scala

object kebian {
  def main(args: Array[String]): Unit = {
    val arr=Array(1,2,4)
    getInt(1,arr:_*)

  }
  def getInt(i:Int,s:Int*): Unit ={
    println(s)
  }
}
