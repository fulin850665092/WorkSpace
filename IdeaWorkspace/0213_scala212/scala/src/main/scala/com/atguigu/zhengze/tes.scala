package com.atguigu.zhengze

object tes {
  def main(args: Array[String]): Unit = {
    scala.math.sqrt(2)
    println(sum(1, 2, 3))
    println(sum(6))
    println(sum(2, 4))
    println(sum(1, 1, 1, 2))
    println(Array("one", "two", "three").max)
    println(Array("one", "two", "three").mkString("-"))
    println(Array(1, 7, 2, 9).sum)
    println(for (elem <- Array(1, 7, 2, 9).sorted) {
      println(elem)
    })
    println(Array(1, 3).zip(Array(2, 4)))
    sou(4)
    println("=========")
    for (i <- 1 to 3;j<- 1 to 3;if i != j) {
      print(10 * i + j)+" "
    }
  }

  def sum(args:Int*): Int = {
    var r = 0
    for (arg <- args) {
      r += arg

    }
    r
  }
  def sou(n:Int)={
   ( 0 to n).reverse foreach print
  }
}
