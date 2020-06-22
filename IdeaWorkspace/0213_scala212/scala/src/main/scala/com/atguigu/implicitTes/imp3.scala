package com.atguigu.implicitTes

import sun.security.util.Password

object imp3 {
  //扩展功能，就可以用隐式转换，和trait
  def main(args: Array[String]): Unit = {

    def regUser(name:String)(implicit password: String="123456")={
      println(s"name=$name+,+password:$password")
    }
    implicit val psd="8888888"

    //不加括号调用：8888888
    //加括号调用：123456
    //自己写调用：66666666
    regUser("zhangsan")("66666666")
  }
}
