package com.atguigu.scala

import scala.collection.immutable

object  ForDemo2 {

  def main(args: Array[String]): Unit = {
    //99乘法
    for (i <- 1 to 9) {
      for (j <- 1 to i) {
         print (s"$j * $i =${i*j}\t")
      }
      println()
    }
    for (i <- 1 to 9 ; j<- 1 to i ) {
      print(s"$j * $i = ${i*j} \t")
      if (i==j) println()
    }
    //for 推导
    val  arrt: immutable.IndexedSeq[Int] =for (i <- 1 to 10)yield i*i*i
    println(arrt)
    println((1 to 10 ).map(_+3))

  }
}
