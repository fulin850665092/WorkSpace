package com.atguigu.scala

import java.io.{File, FileWriter, PrintWriter}

import com.atguigu.String.Stringtset1

import scala.io.{Source, StdIn}

object StringTest {
  def main(args: Array[String]): Unit = {
      val s="sfff"
      val _hh=""
      val _=""
      val * = ""
      val % :String = "dddssd"
      val ## = ""
      val ^ = ""
      val $ = ""
      val ! = ""
      val @@ = ""
      val > = ""
//      val () = ""
//      val ( = ""
      val name ="zs"
      val age =52
      val de=
        s"""
          |aaaaa $age
          |bbbb
          |  ccc  $name
        """.stripMargin
        println(de)
    val `type`="ss"
    println(`type`)
    val stringTest=new Stringtset1
    println(stringTest)
    println(Stringtset1.age)
    val srr="sfhfhfh"
    val str = srr.substring(1)
    println(str)
    //json {"name":"sz","age":33}
    println(s"name=$name,age=$age")
   val jsonStr= """
      |{"name":"sz","age":33}
    """.stripMargin
    println(jsonStr)
//    printStr
    prinRead
    outputWrite
  }

  def  getVal :Unit={
    println("我是scala")
  }
  def :>%():Unit={
      println("我是特殊的scala方法")
  }
  def printStr():Unit={
     val str = StdIn.readInt()
     println("学生的年纪："+str)
  }

  def prinRead() : Unit={
    val str=Source.fromFile("0213_scala212/input/test.txt").getLines()
    while (str.hasNext) {
      println(str.next())
    }
  }

  def outputWrite():Unit={
    val write=new PrintWriter(new FileWriter("0213_scala212/output/test2.txt"))
    write.println("我输出了")
    write.flush()
    write.close()
  }

}
