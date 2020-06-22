package com.atguigu.scala

object tes222 {
  def main(args: Array[String]): Unit = {
    val objUser1 = user444 //伴生对象
    val objUser2 = user444() //调用伴生对象的 apply（）方法 生成伴生类对象
    val classUser2 = new user444()  //调用类的构造器生成对象
    classUser2.get1()
    objUser1.get()
    objUser2.get1()
  }


}
object  user444 {
  def apply(): user444 = new user444()
  def get()={
    println("sss")
  }
}
class  user444 {
  def get1()={
    println("sss")
  }
}

