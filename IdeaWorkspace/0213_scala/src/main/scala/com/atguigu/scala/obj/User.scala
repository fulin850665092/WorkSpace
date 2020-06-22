package com.atguigu.scala.obj

import scala.beans.BeanProperty

class User(@BeanProperty var name :String,@BeanProperty var age :Int,@BeanProperty val sex:String){
  def this () {
    this("a",3,"q")
  }
  def this (name:String) {
    this("a",3,"q")
  }

  def foo()={
    val name ="sss"
    //调用属性
    println(this.name)
    //调用变量
    println(name)
  }
}
// final  String name(java)(常量),val：只有get方法没有set方法，var：get set 都有
//class User(val name : String ,val age : Int)