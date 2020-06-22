package com.atguigu.scala.obj

object Demo1 {
  def main(args: Array[String]): Unit = {
     val user =new User("张三",20,"")
      println(user.age)
      println(user.name)
      user.name="李四"
      user.age=30
      println(user.age)
      println(user.name)
      user.foo
  }
}
