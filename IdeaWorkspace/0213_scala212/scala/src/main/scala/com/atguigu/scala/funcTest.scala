package com.atguigu.scala

import jdk.nashorn.internal.ir.IndexNode

object funcTest {
  def main(args: Array[String]): Unit = {

    def test11()={
      println("我是函数")
    }
     val f1:()=>Unit=test11
     f1()
    //明确返回值类型是Unit，那Unit和=都可以省略，并且，return 无效
    def tes33(){
      return 22
    }
    println(tes33())

    def add (a:Int,b:Int=3)= a+b
    println(add(2))

    def add2 (a:Int,b:Int=3,c:Int)= a+b+c
    println(add2(3,c=4))

    def tes1(num1:Int):Int={
      num1*2
    }
     def tes2(f:(Int)=>Int)={
        f(10)
     }
      val f = tes1 _
      println(tes2(f))

      def  tes (n : Int)( f:Int=>Int)={
        n*f(10)
      }
      println(tes(2)(f))
      val tt=tes2((i:Int)=>{
        i*i
      })
    println(tt)
    //匿名函数，也符合至简原则,下划线（_） 在匿名函数中可以代表参数使用
    val t2=tes2( i=> i*i)
    println(t2)
    val t3=tes2( _*2)
    println(t3)

    def args(names:String*)={
        println(names)
    }
    args("zhans","liss","ingm")

    def getVal(foo:(Int)=>Int)={
      foo(5)
    }
    println(getVal(i=>i+i))

    def getAllVal(f:(Int,Int)=>Int)={
        f(1,2)
    }
    println(getAllVal((x,y)=>x*y))
    println(getAllVal(_ * _))
  }
}
