package com.atguigu.danli

object danli2 {
  def main(args: Array[String]): Unit = {
      val user3342 = User334()
      val user3343 = User334()
      //单例，所以是同一个对象，地址值相同
      println(user3342 eq user3343)
  }
  //私有化构造器
  class User334 private{

  }
  //静态访问
      //  使用伴生对象的对象特性，初始化加载对象
  object User334 {
    val user334= new User334()
    def apply(): User334 = {
      user334
    }
  }
}