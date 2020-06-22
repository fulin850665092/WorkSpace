package com.atguigu.match1

object mat6 {
  def main(args: Array[String]): Unit = {
    //匹配列表
    for (list1 <- Array(List(0),
      List(1, 0),
      List(0, 0, 0),
      List(1, 0, 0),
      List(88))) {
      val result = list1 match {
        case List(0) => "0" //匹配List(0)
        case List(x, y) => x + "," + y //匹配有两个元素的List
        case List(0, _*) => "0 ..."
        case _ => "something else"
      }
      println(result)
    }
      println("================")
      //匹配列表
      val list: List[Int] = List(1, 2, 5, 6, 7)
      list match {
        case first :: second :: rest => println(first + "-" + second + "-" + rest)
        case _ => println("something else")
      }
      println("================")
      //匹配元组
      for (tuple <- Array((0, 1),
        (1, 0),
        (1, 1),
        (1, 0, 2))) {
        val result = tuple match {
          case (0, _) => "0 ..." //是第一个元素是0的元组
          case (y, 0) => "" + y +" "+ "0" // 匹配后一个元素是0的对偶元组
          case (a, b) => "" + a + " " + b
          case _ => "something else" //默认
        }
        println(result)
      }

    }
  }


