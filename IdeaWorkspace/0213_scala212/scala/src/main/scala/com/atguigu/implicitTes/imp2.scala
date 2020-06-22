package com.atguigu.implicitTes

object imp2 {
  //扩展功能，就可以用隐式转换，和trait
  def main(args: Array[String]): Unit = {
    val user = new User
    user.insertUser()
    user.updateUser(user) //直接可以扩展功能

    val user2 = new User with addUserMethod1
    user.updateUser(user2)  //trait 也可以扩展
  }

  class User{
    def insertUser()={
      println("insertUser")
    }
  }
  //2.10 增加次功能，
    //  构造参数必须存在，且只有一个，用于转换类型
    //参数类型就是当前类
  implicit class addUserMethod(user: User){
    def updateUser(user: User): Unit ={
      println(user)
    }
  }
  trait addUserMethod1{
    def updateUser(user: User): Unit ={
      println(user)
    }
  }


}
