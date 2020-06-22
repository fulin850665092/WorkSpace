package com.atguigu.scala

object Tes {
  def main(args: Array[String]): Unit = {
    //todo 函数嵌套
    def tes() ={
      def tes1(i:Int)={
        i*3
      }
      tes1 _
    }
    println(tes()(10))

    //todo 闭包
      //栈帧：先进后出，（压栈/弹栈）
    def tes3() ={
      val j=20
      def tes2(i:Int)={
        i*j
      }
      tes2 _
    }
    println(tes3()(10))
    //todo 惰性函数
    def duox()={
      println("我加载了，te")
    }
   lazy val num = duox()
    println("我加载了")
    println("我加载了")
    println("我加载了")
    println("我加载了")
    println(s"name=$num")
  }
}
