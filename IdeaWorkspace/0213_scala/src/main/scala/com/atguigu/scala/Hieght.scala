package com.atguigu.scala

object Hieght {

  def main(args: Array[String]): Unit = {
    val arr=map(Array(1,2,3),x=>x*x*x)
    println(arr.mkString(","))
//   val arr1= map1(Array(1,2,3),x =>x%2==0)
    val arr1= filter(Array(1,2,3),_ %2==0)
    println(arr1.mkString(","))
//    val  arr2=reduce(Array(1,2,3,4),(x,y)=>x *y)
    val  arr2=reduce(Array(1,2,3,4),_ * _)
    println(arr2)
  }
  def  map(array: Array[Int],op:Int=>Int):Array[Int]={
    for (elem <- array) yield op(elem)
  }

  def filter(arr:Array[Int],op:Int=>Boolean)={
    for (elem <- arr if op(elem)) yield elem
  }

  def reduce(arr:Array[Int],op:(Int,Int)=>Int)={
    var init=arr(0)
    for (elem <- 1 until arr.length) {
      init=op(init,arr(elem))
    }
    init
  }
  add(2)(3)
  def add(a: Int)(b: Int)= {
    a+b
    println(a+b)
  }
}
