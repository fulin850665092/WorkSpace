package com.atguigu.scala

import scala.util.control.Breaks._

object fun2 {
  def main(args: Array[String]): Unit = {
    //TODO breakable 其实是函数
      //这里的{}其实就是函数的参数列表（小括号）
      //for循环就是一段代码逻辑
      //模版方法设计模式
    breakable (
      for (elem <- 1 to 5) {
        if (elem == 3) break
        println(elem)
      }
    )
    //TODO 控制抽象函数
    def te(op: =>Unit):Unit={
      op
    }
    te(println("za"))
    te{
      val name ="zs"
      val hello="hello"
      println(s"$hello,$name")
    }
    //TODO 递归函数
      //1.递归算法的数据处理有规律
      //2。递归方法内部应该调用自身
      //3。递归算法应该有跳出递归的逻辑操作
      //4。递归方法应该明确返回值类型
      //阶乘算法：5！
    def te1(i:Int):Int={
        if (i==1) 1 else  i*te1(i-1)
      }
    println(te1(5))
    //报错：StackOverflowError
//    println(te1(5000000))

    //TODO 尾递归
//    def te41():Unit={
//      println("zzz")
//      //再次调用和第一次调用没联系，第一次调用的会被销毁
//        //第一次栈内存释放
//      te41()
//    }
//    def te4():Unit={
//      //第一次调用没结束，栈内存没释放
//      te4()
//      println("zzz")
//    }
//    println(te4())
    def tail(i:Int,reslut:Int):Int={
      if(i<=1) reslut else tail(i-1,i+reslut)
    }
    println(tail(5,1))

    //todo 函数煸炒  延迟加载（惰性加载）
      //数据最好的使用方式
          //1。用的数据的时候在加载
          //2。暂时用不到的数据，先不加载

    def te3()={
      println("te3")
      "zhangsan"
    }
    //如果变量声明lazy关键字，那么只有在使用的时候才会初始化
    lazy val name =te3();
    println("======")
    println("======")
    println("======")
    println(name)

    def te22(d:Int=2)={
      println(d)
    }
    te22()
    test
  }
}
