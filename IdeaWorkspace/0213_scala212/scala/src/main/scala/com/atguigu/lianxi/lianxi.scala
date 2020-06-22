package com.atguigu.lianxi

object lianxi {
  def main(args: Array[String]): Unit = {

    //11. 如果想把一个任意的数字A通过转换后得到它的2倍，那么这个转换的函数应该如何声明和使用
    def to2(a:Double)={
        a*2
    }
    println(to2(2))
    //2. 如果上一题想将数字A转换为任意数据B（不局限为数字），转换规则自己定义，该如何声明
    def chang (a:Double)(b:(Double)=>Any)={
      b(a)
    }
    println(chang(4)(_.toString))
    //3如果函数调用：  test(10,20,c)的计算结果由参数c来决定，这个参数c你会如何声明
    def keli(a: Int)(b: Int)(c: (Int, Int) => Int) = {
      c(a, b)
    }

    println(keli(10)(20)(_ + _))

    //4,如果设计一个用于过滤的函数，你会如何做？
    def etl(str: String):String = {
      val strs = str.split(" ")
      if (strs.length<=0||str=="") return ""
      val sb=new StringBuilder()
      for (i <- 0 to strs.length-1) {
        if (strs(i).substring(0, 1)=="s") sb.append(strs(i)+",")
      }
       sb.deleteCharAt(sb.length-1).toString()
    }
    println(etl("hello world scala spark"))
    println(etl(" "))
  }
}
