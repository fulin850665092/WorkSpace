package com.atguigu.trait_tes

object tes2{
  def main(args: Array[String]): Unit = {
      new User().getStackTrace
  }
}
//特质；可以作为接口使用
    //可以作为抽象类使用
trait tes2 extends java.lang.Exception{
    //抽象属性
    val name :String
    //完整属性
    val age:Int=20
    //抽象方法
    def te1()
    //完整方法
    def te2()={
      println("te2")
    }
}
//类如果混入特质，必须将其中的抽象类补全，否则为抽象类
class User extends tes2 {
  override val name: String = "张三"
  //也可以重写非抽象属性
  override val age: Int = 10
  override def te1(): Unit ={
    println("User")
  }
  //也可以重写非抽象方法
  override def te2(): Unit = {

  }
}
