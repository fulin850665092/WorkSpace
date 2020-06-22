package com.atguigu.lianxi3

class Task3 extends Serializable {

  var data: Int = 0
  var logic: Int => Int = null

  def compute() = {
    logic(data)
  }
}
