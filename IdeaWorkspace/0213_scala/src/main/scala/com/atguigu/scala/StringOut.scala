package com.atguigu.scala

import java.io.{FileWriter, PrintWriter}

import scala.io.{Source, StdIn}

object StringOut {
  def main(args: Array[String]): Unit = {

     val a = 20
    printf("a的值是：%d",a)
    val s =
      """
        |select
        |name,
        |age,
        |sex
        |from user
        |where name = 'sz'
      """
    printf(s)

    val name = "sz"
    val age = 20
    val s1 =
       s"""
        |select
        |name,
        |age,
        |sex
        |from user
        |where name = '${name}' and age = '${age+2}'
      """.stripMargin
    println(s1)

    val sa = s"name=${name}"
    println(sa)

//    val line = StdIn.readLine("请输入你的银行卡密码：")
//    println(line)
    prinRead
    outputWrite
  }
  def prinRead() : Unit={
    val str=Source.fromFile("input/test.txt").getLines()
    while (str.hasNext) {
      println(str.next())
    }
  }

  def outputWrite():Unit={
    val write=new PrintWriter(new FileWriter("output/test1.txt"))
    write.println("我输出了")
    write.flush()
    write.close()
  }
}
