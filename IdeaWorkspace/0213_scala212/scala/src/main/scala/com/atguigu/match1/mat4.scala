package com.atguigu.match1

object mat4 {
  //todo：  匹配类型,scala 在执行模式匹配时，匹配类型是不考虑范型的
    //  todo： someThing  等于_
        //todo 数组（Array）后面那个类型其实不是范型，而是数组元素的类型，编译后就不是范型了，执行匹配时，就匹配不上
  def main(args: Array[String]): Unit = {
      val list = List(1,2,3,4,List(5,6))
      println(list.flatMap(dt => {
        dt match {
          case a: List[_] => a
          case b => List(b) //实际b：_，b只是取了个名
        }
      }))
      val list2 = List(List(1,2,3,4),List(5,6))
      println(list2.flatMap(dt=>dt))
      println(describe(Array("1",2,4)))
      println(describe(List("1",2,4)))
  }
  def describe(x: Any) = x match {
    case i: Int => "Int"
    case s: String => "String hello"
    case m: List[_] => "List"
    case c: Array[Int] => "Array[Int]"
    case someThing => "something else " + someThing
  }


}
