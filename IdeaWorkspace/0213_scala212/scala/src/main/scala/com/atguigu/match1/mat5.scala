package com.atguigu.match1

object mat5 {
  def main(args: Array[String]): Unit = {
    //匹配数组
    for (arr <- Array(Array(0),
      Array(1, 0),
      Array(0, 1, 0),
      Array(1, 1, 0),
      Array(1, 1, 0, 1),
      Array("hello", 90))) { // 对一个数组集合进行遍历

      val result = arr match {
        case Array(0) => "0" //匹配Array(0) 这个数组
        case Array(x, y) => x + "," + y //匹配有两个元素的数组，然后将将元素值赋给对应的x,y
        case Array(0, _*) => "以0开头的数组" //匹配以0开头和数组
        case _ => "something else"
      }
      println("result = " + result)
    }
  }

}
