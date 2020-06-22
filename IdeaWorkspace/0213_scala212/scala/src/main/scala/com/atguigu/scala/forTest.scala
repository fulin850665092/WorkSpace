package com.atguigu.scala

import scala.util.control.Breaks._

object forTest {
  def main(args: Array[String]): Unit = {

    val  result=for (elem <- 1 to 10) yield {
      println(elem)
       elem*2
    }
    println(result)

   for (i <- 1 to 9; j <- 1 to i) yield {
       print(s"$i*$j=${i*j} ")
       if (i==j) println()
    }

//    for (elem <- 1 to 6){
//      println(elem)
//      if (elem==3) return
//    }
    println(2)
    //._引入包下的所有包
     breakable(
         for (elem <- 1 to 6) {
           println(elem)
           if (elem == 3)  break
         }
     )
    for (elem <- 1 to 18 by 2; j=(18-elem)/2) {
        println(" "*j + "*"*elem)
    }
  }
}
