package com.atguigu.scala

import com.atguigu.java.My

/**
  * var:变量， 可以重复赋值
  * val：常量，不可以重复赋值
  * 注意：能用常量就不用变量，安全些
  */
object  VarDemo {
  def main(args: Array[String]): Unit = {
    var a:Int=10
    a = 20
    val b:Int=29;
    println("我是变量，我可以变："+a)
    println("我是常量，我不可以变："+b)
    var c = 10;
    val - =1;
    val -- =9;
    println(--)
    val ` ` =2;
    println(` `)
    var `type` = 100;
    val my=new My();
    my.`type`
    println(my.a)


  }
}
