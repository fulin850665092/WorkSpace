package com.atguigu.scala

object function {
  def main(args: Array[String]): Unit = {
    val sum =add(1,2)
    println(sum)
    println(add1(1,2))

  }

  def add(a:Int,b :Int) :Int={
    //具体实现
    a+b
  }
  def add1(a:Int,b :Int) ={
    //具体实现
    a+b
  }
  //函数传递
  val f=foo _
  def foo()={
    println("foo ...")
    1
  }
  //可以之间调用f，和调用foo一样
  f()
  foo()
  //f1:就是高阶函数（算子），以函数作为参数
  def f1( f:(Int,Int)=>Int): Int ={
      f(2,4)
  }
  println(f1(add3))
  def  add3(a : Int,b:Int):Int={
    a*b
  }

  def apM(a:Int)={
    println(a*a)
  }
  def  aperation(aee:Array[Int],ap:Int=>Unit)={
    for (elem <- aee) {
      ap(elem)
    }
  }
  //调用已有函数
  aperation(Array(1,2,3),apM)
  //可以使用命名函数
  aperation(Array(1,2,3),(ele:Int)=>{
    println(ele*ele)
  })
  //传参匿名函数，参数类型可以省略，会自己推断
  aperation(Array(1,2,3),(ele)=>{
    val i=ele+1
    println(ele*ele)
  })
  //传参匿名函数，参数类型可以省略，只有一个参数，类型可以省略，其他不能省略（）
  aperation(Array(1,2,3),ele=>{
    val i=ele+1
    println(ele*ele)
  })
  //传参匿名函数，参数类型可以省略，只有一个参数，类型可以省略，其他不能省略（）
  //匿名函数只有一行，花括号也能省略

  aperation(Array(1,2,3),ele=>ele+1
  )
}
