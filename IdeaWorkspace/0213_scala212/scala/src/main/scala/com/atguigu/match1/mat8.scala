package com.atguigu.match1

object mat8 {
  def main(args: Array[String]): Unit = {
    val user2 = new User("zhangsan",20)
    user2 match {
      case User("zhangsan",21)=> println("匹配")
      case _=> println("不匹配")
    }
  }
  //样例类（啥都有）
    //参数默认便是属性，不需要加val，var修改，要修改，要加var，底层默认是val
  case class User(name:String,age:Int)

}


