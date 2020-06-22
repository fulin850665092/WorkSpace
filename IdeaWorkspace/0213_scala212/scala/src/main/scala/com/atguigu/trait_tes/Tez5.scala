package com.atguigu.trait_tes

object Tez5 {
  // trait中的 super 指的是上一个特质，而不是上一级特质，如果想指定特质，那么使用super[指定特质名]
  def main(args: Array[String]): Unit = {
    val user3 = new User33()
    user3.createData()
  }
}
trait oparea{
  println(1)
  def createData()={
    println("操作数据")
  }
}

trait  Mysql extends oparea{
  println(2)
  override def createData(): Unit = {
    print("向数据库中")
    super.createData()
  }
}
trait  hive extends oparea{
  println(3)
  override def createData(): Unit = {
    print("向hive中")
//    super.createData()
    super[oparea].createData()
  }
}

class User33 extends Mysql  with  hive{

}