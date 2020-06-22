package com.atguigu.trait_tes

object meiju {
  def main(args: Array[String]): Unit = {
    val blue = Color.BLUE
  }
}

object  Color extends Enumeration{
  val RED =Value(1,"red")
  val  BLUE =Value(2,"blue")
  val  YELLOW =Value(3,"yellow")

 }
object test extends App{
  println("a")
  println("b")
  println("c")
}