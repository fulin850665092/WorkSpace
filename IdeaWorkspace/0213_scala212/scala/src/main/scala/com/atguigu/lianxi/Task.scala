package com.atguigu.lianxi

class Task extends Serializable {
  var num:Int= _
  var num1:Int= _
  var logic:(Int,Int)=>Int= _
  def count()={
    logic(num,num1)
  }
}
