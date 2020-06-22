package com.atguigu.lianxi2

class Task1 extends Serializable {
  var list:List[String]= _
  var list1:List[(String,String)]= _
  var logic:(List[String])=>List[String]= _
  var logic1:(List[(String,String)])=>List[String]= _
  def count()={
    logic(list)
  }
  def count1()={
    logic1(list1)
  }
}
