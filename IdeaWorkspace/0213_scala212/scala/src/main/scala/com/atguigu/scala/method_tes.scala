package com.atguigu.scala

import scala.beans.BeanProperty

object method_tes{
  def main(args: Array[String]): Unit = {
    val user= new method_tes(name = "zhansgan")
    println(user)
    val user1=new method_tes()
    println(user1)
    val user2=new method_tes(2)
    println(user2)
    val user00=new User115()
    val user116=new User116("zs",20)
//    user116.age=90
    user116.name="ls"
    println(user116.getAge)
    println(user116.name)
    println(user116.age)
    val user118=new User118
    println(user118.name)
    user118.tes1()
    println(user118.age)
    user118.getAge()
  }

}
class method_tes(name:String) {
  //空参构造器
  def this()={
    this("zjamhs")
  }
  def this(age:Int)={
    this()
  }
}
class User112(name:String){
  println("111")
  def this()={
    this("")
    println("222")
  }
}
class User113(name:String) extends User112("zs") {
  println("333")
   def this()={
     this("ls")
     println("444")
   }
}
class User114(name:String) extends User112(name) {
  println("333")
  def this()={
    this("ls")
    println("444")
  }
}
class User115(name:String) extends User112 {
  println("333")
  def this()={
    this("ls")
    println("444")
  }
}
//var:修饰可变，val：修饰不可变
class User116( var name:String, @BeanProperty val age:Int){

}

abstract class  User117(){
  var name:String
  val age:Int=20
  def tes()
  def tes1()={
    println("User 117")
  }
  def getAge()={
    println(age)
  }
}
 class User118 extends User117{
  override var name: String = "张三"
   override val age: Int = 30
   override def tes(): Unit = {
     println("tes")
   }

   override def tes1(): Unit ={
     println("User118")
   }
 }